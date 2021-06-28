package com.shpp.p2p.cs.emalahov.assignment15;

import java.io.*;
import java.util.*;

public class Archive {

    //Green color.
    public static final String GREEN = "\u001B[32m";

    /**
     * This method archive data from fileIn to fileOut in bytes.
     *
     * @param fileIn  input file
     * @param fileOut output archived file with bytes.
     */
    public void archive(String fileIn, String fileOut) {
        long startOfTheProgram = System.currentTimeMillis();
        File file = new File(fileIn);

        HashMap<Byte, Integer> frequencyOfBytes = countFrequency(file);
        EncodingTreeNode tree = buildHuffmanTree(frequencyOfBytes);
        LinkedList<Boolean> treeShape = new LinkedList<>();
        LinkedList<Byte> treeLeaves = new LinkedList<>();
        HashMap<Byte,String> table = makeTableAndFlattenTree(tree,treeShape,treeLeaves);

        short treeSize = (short) treeShape.size();
        long inputDataSize = file.length();

        try(FileOutputStream fos = new FileOutputStream(fileOut)){
            fos.write(shortToByteArray(treeSize));
            archivedTreeShape(treeShape,fos);
            archivedLeaves(treeLeaves,fos);
            archivedData(table,fos);

        } catch (FileNotFoundException e) {
            System.err.println("archive: Output problem");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e1) {
            System.err.println("archive: Error writing file.");
            e1.printStackTrace();
            System.exit(0);
        }

        long outputDataSize = new File(fileOut).length();
        long endOfTheProgram = System.currentTimeMillis();
        long workTime = endOfTheProgram - startOfTheProgram;

        System.out.println(GREEN + "Archive");
        System.out.println("compression efficiency: " + (outputDataSize * 100) / inputDataSize + " %");
        System.out.println("input file: "+ fileIn + " output file: " + fileOut);
        System.out.println("compression time: " + workTime / 1000 + " seconds or " + workTime + " milliseconds");
        System.out.println("input file size: " + inputDataSize + " bytes");
        System.out.println("output file size: " + outputDataSize + " bytes");
    }

    /**
     * This method archived treeShape to the fileOut.
     *
     * @param treeShape shape of the coding tree.
     * @param fos FileOutputStream.
     */
    private void archivedTreeShape(LinkedList<Boolean> treeShape, FileOutputStream fos) {
        try{
            StringBuilder sb = new StringBuilder();
            for(Boolean bool:treeShape){
                if(bool) sb.append(1);
                else sb.append(0);
                if(sb.length() >= 8){
                    String s = sb.substring(0,8);
                    fos.write((byte)Integer.parseInt(s,2));
                    sb.replace(0, 8, "");
                }
            }
            int left = (8-sb.length()%8);
            if(left != 8){
                for(int i = 0; i < left; i++){
                    sb.append(0);
                    if(sb.length() >= 8){
                        String s = sb.substring(0,8);
                        fos.write((byte)Integer.parseInt(s,2));
                        sb.replace(0, 8, "");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("archivedTreeShape: Error writing file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method writes to file leaves of the tree.
     * (leaves writing to file in strict order and only unique)
     * Due to this we will be able to restore the archive table.
     *
     * @param treeLeaves leaves of the coding tree.
     * @param fos FileOutputStream
     */
    private void archivedLeaves(LinkedList<Byte> treeLeaves, FileOutputStream fos) {
        try {
            for(byte b: treeLeaves){
                fos.write(b);
            }
        } catch (IOException e) {
            System.err.println("archivedLeaves: Error writing file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method archive data from input file to his unique bits
     * and then makes bytes of length 8 bit and write it to the output file.
     *
     * @param table Table of unique bytes and their unique bit codes.
     * @param fos FileOutputStream.
     */
    private void archivedData(HashMap<Byte,String> table, FileOutputStream fos) {
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : buffer) {
                sb.append(table.get(b));
                if (sb.length() >= 8) {
                    String s = sb.substring(0, 8);
                    fos.write((byte) Integer.parseInt(s, 2));
                    sb.replace(0, 8, "");
                }
            }
            //Number of zeros that we add to form the last byte.
            short left = (short)(8-sb.length()%8);
            if(left != 8){
                for(int i = 0; i < left; i++){
                    sb.append(0);
                    if(sb.length() >= 8){
                        String s = sb.substring(0,8);
                        fos.write((byte)Integer.parseInt(s,2));
                        sb.replace(0, 8, "");
                    }
                }
            }else {
                left = 0;
            }
            //I write left to the file, so that when unarchiving, correctly read the last byte.
            fos.write(shortToByteArray(left));
        } catch (IOException e) {
            System.err.println("archiveData: Error writing file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method creates HaspMap(table) which contains
     * sequence of unique bits codes of each bytes. Also, this method
     * fills in treeShape and treeLeaves.
     *
     * @param tree main node of the coding tree.
     * @param treeShape shape of the coding tree.
     * @param treeLeaves leaves of the coding tree.
     * @return HaspMap(table) with key: unique byte and value: unique bit code.
     */
    private HashMap<Byte,String> makeTableAndFlattenTree(EncodingTreeNode tree, LinkedList<Boolean> treeShape, LinkedList<Byte> treeLeaves) {
        HashMap<Byte,String> table = new LinkedHashMap<>();
        Stack<EncodingTreeNode> stack = new Stack<>();
        String bitCode = "";
        stack.push(tree);
        treeShape.add(true);

        while(!stack.isEmpty()){
            EncodingTreeNode node = stack.peek();
            //Check if fileIn contains only 1 byte(symbol).
            if(node.rightChild == null && node.leftChild == null && node.frequency > 0){
                bitCode+=0;
                table.put(node.letter,bitCode);
                treeShape.add(false);
                treeLeaves.add(node.letter);
            }
            if(node.leftChild != null && !node.leftChild.isVisited){
                bitCode+=0;
                if(node.leftChild.letter != null){
                    table.put(node.leftChild.letter,bitCode);
                    node.leftChild.isVisited = true;
                    bitCode = bitCode.substring(0,bitCode.length()-1);
                    treeShape.add(false);
                    treeLeaves.add(node.leftChild.letter);
                }else{
                    stack.push(node.leftChild);
                    node.leftChild.isVisited = true;
                    treeShape.add(true);
                }
            }else if(node.rightChild != null && !node.rightChild.isVisited){
                bitCode+=1;
                if(node.rightChild.letter != null){
                    table.put(node.rightChild.letter,bitCode);
                    node.rightChild.isVisited = true;
                    bitCode = bitCode.substring(0,bitCode.length()-1);
                    treeShape.add(false);
                    treeLeaves.add(node.rightChild.letter);
                }else{
                    stack.push(node.rightChild);
                    node.rightChild.isVisited = true;
                    treeShape.add(true);
                }
            }else{
                stack.pop();
                //If we`ve already visited the left and right nodes, then we return to the previous node.
                if(bitCode.length() > 0) bitCode = bitCode.substring(0,bitCode.length()-1);
            }
        }
        return table;
    }

    /**
     * This method build Huffman tree, which we will use to encode the fileIn.
     *
     * @param frequencyOfBytes the frequency of each byte in the file.
     * @return main node of the coding tree.
     */
    private static EncodingTreeNode buildHuffmanTree(HashMap<Byte, Integer> frequencyOfBytes ){
        PriorityQueue<EncodingTreeNode> queue = new PriorityQueue<>();
        for(Byte b: frequencyOfBytes.keySet()){
            queue.add(new EncodingTreeNode(b,frequencyOfBytes.get(b)));
        }

        while (queue.size() > 1){
            EncodingTreeNode leftChild = queue.remove();
            EncodingTreeNode rightChild = queue.remove();

            EncodingTreeNode father = new EncodingTreeNode(
                    null,
                    leftChild.frequency+rightChild.frequency,
                    leftChild,
                    rightChild);
            queue.add(father);
        }

        return queue.remove();
    }

    //Array of input data bytes.
    private byte[] buffer;

    /**
     * This method reads all bytes from the fileIn to the buffer array,
     * and count their frequency and fills in the HaspMap(frequencyOfBytes)
     * key: unique byte value: frequency.
     *
     * @param file fileIn.
     * @return HaspMap(frequencyOfBytes).
     */
    private HashMap<Byte, Integer> countFrequency(File file){
        HashMap<Byte, Integer> frequencyOfBytes = new LinkedHashMap<>();
        try(FileInputStream fis = new FileInputStream(file)){
            buffer = new byte[(int) file.length()];
            fis.read(buffer);
            for (byte b : buffer) {
                frequencyOfBytes.merge(b, 1, Integer::sum);
                /*if(counter != null){
                    frequencyOfBytes.put(buffer[i],counter+1);
                }else{
                    frequencyOfBytes.put(buffer[i],1);
                }*/
            }
        } catch (FileNotFoundException e) {
            System.err.println("countFrequency: Input problem");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e1) {
            System.err.println("countFrequency: Error reading file.");
            e1.printStackTrace();
            System.exit(0);
        }
        return frequencyOfBytes;
    }

    /**
     * This method converts "short" to 2 length byte array.
     *
     * @param value short number.
     * @return 2 length array.
     */
    private byte[] shortToByteArray(short value){
        return new byte[]{
                (byte)(value >> 8),
                (byte) value};

    }
}
