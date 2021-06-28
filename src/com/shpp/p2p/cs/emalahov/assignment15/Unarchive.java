package com.shpp.p2p.cs.emalahov.assignment15;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Unarchive {

    public static final String CYAN = "\u001B[36m";

    /**
     * This method unarchive data from fileIn in bytes to fileOut in its original form.
     *
     * @param fileIn  input file.
     * @param fileOut output file.
     */
    public void unarchive(String fileIn, String fileOut){
        long startOfTheProgram = System.currentTimeMillis();
        File file = new File(fileIn);

        try (FileInputStream fis = new FileInputStream(file);FileOutputStream fos = new FileOutputStream(fileOut)) {

            byte[] treeSizeArray = new byte[2];
            fis.read(treeSizeArray);
            short treeSize = ByteBuffer.wrap(treeSizeArray).getShort();

            LinkedList<Boolean> treeShape = unarchiveTreeShape(treeSize,fis);
            EncodingTreeNode tree = unflattenTree(treeShape,fis);
            HashMap<String,Byte> table = makeTable(tree);
            //Read all bytes to the buffer array.
            byte[] buffer = fis.readAllBytes();
            //Read the last 2 bytes in order to restore left.
            fis.getChannel().position(fis.getChannel().size()-2);
            byte[] leftArray = new byte[2];
            fis.read(leftArray);
            //Number of zeros that we add to form the last byte.
            short left = ByteBuffer.wrap(leftArray).getShort();
            unarchivedData(buffer,table,fos,left);

        } catch (FileNotFoundException e) {
            System.err.println("unarchive: Input problem");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e1) {
            System.err.println("unarchive: Error reading file.");
            e1.printStackTrace();
            System.exit(0);
        }
        long endOfTheProgram = System.currentTimeMillis();
        long workTime = endOfTheProgram - startOfTheProgram;

        System.out.println(CYAN + "Unarchive");
        System.out.println("compression time: " + workTime / 1000 + " seconds or " + workTime + " milliseconds");
        System.out.println("input file: "+ fileIn + " output file: " + fileOut);
    }

    /**
     * This method unarchive data from input file of byte
     * to the output file in its original form.
     *
     * @param buffer array of all bytes read from file.
     * @param table table of unique bytes and their unique bit codes.
     * @param fos FileOutputStream
     * @param left Number of zeros that we add to form a byte.
     */
    private void unarchivedData(byte[] buffer, HashMap<String, Byte> table, FileOutputStream fos, short left) {
        try {
            StringBuilder sb = new StringBuilder();
            String str = "";
            //Check if we didn't add zeros to form bytes.
            if(left == 0){
                for(int i = 0; i < buffer.length-2; i++){
                    sb.append(String.format("%8s",
                            Integer.toBinaryString((buffer[i] + 256) % 256)).replace(" ", "0"));
                    for(int j = 0; j < sb.length(); j++){
                        str+=sb.charAt(j);
                        if(table.containsKey(str)){
                            byte ch = table.get(str);
                            fos.write(ch);
                            str = "";
                        }
                    }
                    sb = new StringBuilder();
                }
            }else {
                //-2 because i write short in 2 length byte array int end of file.
                for(int i = 0; i < buffer.length-2; i++){
                    sb.append(String.format("%8s",
                            Integer.toBinaryString((buffer[i] + 256) % 256)).replace(" ", "0"));
                    //If "i" is the last number in our data:
                    if(i == buffer.length - 3){
                        for(int j = 0; j < (8-left); j++){
                            str+=sb.charAt(j);
                            if(table.containsKey(str)){
                                byte ch = table.get(str);
                                fos.write(ch);
                                str = "";
                            }
                        }
                        break;
                    }
                    //If "i" isn't the last number:
                    for(int j = 0; j < sb.length(); j++){
                        str+=sb.charAt(j);
                        if(table.containsKey(str)){
                            byte ch = table.get(str);
                            fos.write(ch);
                            str = "";
                        }
                    }
                    sb = new StringBuilder();
                }
            }
        }catch (IOException e) {
            System.err.println("unarchivedData: Error reading file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method recover the tree shape,
     * due to this we will recover the tree correctly.
     *
     * @param treeSize size of the tree coded sequences.
     * @param fis FileInputStream
     * @return shape of the tree.
     */
    private LinkedList<Boolean> unarchiveTreeShape(long treeSize, FileInputStream fis) {
        LinkedList<Boolean> treeShape = new LinkedList<>();
        try{
            StringBuilder sb = new StringBuilder();
            while (treeShape.size() != treeSize){
                byte b = (byte) fis.read();
                sb.append(String.format("%8s",
                        Integer.toBinaryString((b + 256) % 256)).replace(" ", "0"));
                for(int i = 0; i < sb.length(); i++){
                    if(sb.charAt(i) == '1'){
                        treeShape.add(true);
                    }else{
                        treeShape.add(false);
                    }
                    if(treeShape.size() == treeSize){
                        break;
                    }
                }
                sb = new StringBuilder();
            }
        } catch (FileNotFoundException e) {
            System.err.println("unarchiveTreeShape: Input problem");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e1) {
            System.err.println("unarchiveTreeShape: Error reading file.");
            e1.printStackTrace();
            System.exit(0);
        }
        return treeShape;
    }

    /**
     * This method restores the encoding tree.
     * And read archived leaves and put them to the correct node.
     *
     * @param treeShape shape of the tree.
     * @param fis FileInputStream
     * @return main node of the coding tree.
     */
    private EncodingTreeNode unflattenTree(LinkedList<Boolean> treeShape,FileInputStream fis) throws IOException {
        Stack<EncodingTreeNode> stack = new Stack<>();
        EncodingTreeNode father = new EncodingTreeNode();
        stack.push(father);
        for(int i = 1; i < treeShape.size();i++){
            EncodingTreeNode previousNode = stack.peek();
            if(treeShape.get(i)){
                if(previousNode.leftChild == null){
                    previousNode.leftChild = new EncodingTreeNode();
                    stack.push(previousNode.leftChild);
                }else if(previousNode.rightChild == null){
                    previousNode.rightChild = new EncodingTreeNode();
                    stack.pop();
                    stack.push(previousNode.rightChild);
                }
            }else {
                if(previousNode.leftChild == null){
                    previousNode.leftChild = new EncodingTreeNode((byte)fis.read());
                }else if(previousNode.rightChild == null){
                    previousNode.rightChild = new EncodingTreeNode((byte)fis.read());
                    stack.pop();
                }
            }
        }
        return father;
    }

    /**
     * This method restores HaspMap(table) which contains
     * sequence of unique bits code of each bytes.
     *
     * @param tree main node of the coding tree.
     * @return HaspMap(table) with key: unique bit code and value: unique byte.
     */
    private HashMap<String,Byte> makeTable(EncodingTreeNode tree) {
        HashMap<String,Byte> table = new LinkedHashMap<>();
        Stack<EncodingTreeNode> stack = new Stack<>();
        String bitCode = "";
        stack.push(tree);

        while(!stack.isEmpty()){
            EncodingTreeNode node = stack.peek();
            if(node.leftChild != null && !node.leftChild.isVisited){
                bitCode+=0;
                if(node.leftChild.letter != null){
                    table.put(bitCode,node.leftChild.letter);
                    node.leftChild.isVisited = true;
                    bitCode = bitCode.substring(0,bitCode.length()-1);
                }else{
                    stack.push(node.leftChild);
                    node.leftChild.isVisited = true;
                }
            }else if(node.rightChild != null && !node.rightChild.isVisited){
                bitCode+=1;
                if(node.rightChild.letter != null){
                    table.put(bitCode,node.rightChild.letter);
                    node.rightChild.isVisited = true;
                    bitCode = bitCode.substring(0,bitCode.length()-1);
                }else{
                    stack.push(node.rightChild);
                    node.rightChild.isVisited = true;
                }
            }else{
                stack.pop();
                if(bitCode.length() > 0) bitCode = bitCode.substring(0,bitCode.length()-1);
            }
        }
        return table;
    }

    /*private static StringBuilder decodeText(EncodingTreeNode tree, LinkedList<Boolean> messageBits){
        StringBuilder result = new StringBuilder();
        Stack<EncodingTreeNode> stack = new Stack<>();
        stack.push(tree);
        for(int i = 0;i < messageBits.size();i++){
            EncodingTreeNode node = stack.pop();
            if(!messageBits.get(i)){
                if(node.leftChild != null){
                    if(node.leftChild.letter != null){
                        result.append(node.leftChild.letter);
                        stack.push(tree);
                    }else{
                        stack.push(node.leftChild);
                    }
                }
            }else{
                if(node.rightChild != null){
                    if(node.rightChild.letter != null){
                        result.append(node.rightChild.letter);
                        stack.push(tree);
                    }else{
                        stack.push(node.rightChild);
                    }
                }
            }
        }
        return result;
    }*/
}
