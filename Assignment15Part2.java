package com.shpp.p2p.cs.emalahov.assignment15;

public class Assignment15Part2 {
    public static void main(String[] args) {
        Archive archive = new Archive();
        Unarchive unarchive = new Unarchive();
        if (args.length == 0) {
            archive.archive("res/moo.wav", "res/moo.wav.par");
            unarchive.unarchive("res/moo.wav.par", "res/moo.wav.uar");
        } else if (args.length == 1) {
            if (args[0].endsWith(".par")) {
                String param2 = args[0].substring(0, args[0].length() - 4);
                unarchive.unarchive(args[0], param2 + ".uar");
            } else {
                archive.archive(args[0], args[0] + ".par");
            }
        } else if (args.length == 2) {
            if (args[0].endsWith(".par")) {
                unarchive.unarchive(args[0], args[1]);
            } else {
                archive.archive(args[0], args[1]);
            }
        } else if(args.length == 3) {
            if (args[0].equals("-a")) {
                archive.archive(args[1], args[2]);
            } else if(args[0].equals("-u")) {
                unarchive.unarchive(args[1], args[2]);
            }else{
                System.err.println("Incorrect input!");
            }
        }else{
            System.err.println("Incorrect input!");
            System.err.println("Please enter \"nameOfFile.type\" if you want to archive data");
            System.err.println("Please enter \"nameOfFile.type.par\" if you want to unarchive data");
            System.err.println("Please enter \"nameOfFile.type\" \"nameOfFile.type.par\" if you want to archive data from first file to the second");
            System.err.println("Please enter \"nameOfFile.type.par\" \"nameOfFile.type.uar\" if you want to unarchive data from first file to the second");
            System.err.println("Please enter \"-a\" \"nameOfFile.type\" \"nameOfFile.type\" if you want to archive data from first file to the second");
            System.err.println("Please enter \"-u\" \"nameOfFile.type\" \"nameOfFile.type\" if you want to unarchive data from first file to the second");
        }
    }
}
