package com.shpp.p2p.cs.emalahov.assignment15.test;

import com.shpp.p2p.cs.emalahov.assignment15.Archive;
import com.shpp.p2p.cs.emalahov.assignment15.Unarchive;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.fullTest("res/ababcab.txt");
        test.fullTest("res/alphaonce.txt");
        test.fullTest("res/alphatwice.txt");
        test.fullTest("res/as.txt");
        test.fullTest("res/asciiart.txt");
        test.fullTest("res/bender.jpg");
        test.fullTest("res/black.png");
        test.fullTest("res/d.txt");
        test.fullTest("res/dictionary.txt");
        test.fullTest("res/nonrepeated.txt");
        test.fullTest("res/example.txt");
        test.fullTest("res/excellent.wav");
        test.fullTest("res/fibonacci.txt");
        test.fullTest("res/hamlet.txt");
        test.fullTest("res/hellokitty.bmp");
        test.fullTest("res/large.txt");
        test.fullTest("res/larger.txt");
        test.fullTest("res/medium.txt");
        test.fullTest("res/moo.wav");
        test.fullTest("res/poem.txt");
        test.fullTest("res/secretmessage.txt");
        test.fullTest("res/short.txt");
        test.fullTest("res/singlechar.txt");
        test.fullTest("res/tomsawyer.txt");
    }

    private void fullTest(String file){
        Archive archive = new Archive();
        Unarchive unarchive = new Unarchive();
        String str = file.substring(0,file.length()-4);
        archive.archive(file,str+".par");
        unarchive.unarchive(str+".par",str+".uar");
    }
}
