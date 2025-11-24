package problem;

import java.util.ArrayList;

import java.util.List;


import FuzzySet.FuzzySet;
import MemberFunction.IMemberFunction;
import MemberFunction.TrapzoidFunction;
import MemberFunction.TriangleFunction;
import Rule.IRule;
import Utils.GetY;

public class CaseStudy {

    public static void main(String[] args) {
       // List<Double> input = List.of(60.0,25.0);
        GetY getYUtil = new GetY();
    
        // Study Preparation
        String studyPreparationVar = "StudyPreparation"
         , fuzzySetSP1 = "poor" , fuzzySetSP2 = "average" , fuzzySetSP3 = "excellent";

        FuzzySet sp_fs = new FuzzySet();

        IMemberFunction sp_mf1 = new TrapzoidFunction(fuzzySetSP1, List.of(0.0, 0.0, 30.0, 50.0), getYUtil.getY(List.of(0.0, 0.0, 30.0, 50.0)));
        sp_fs.addMemberFunction(sp_mf1);
        IMemberFunction sp_mf2 = new TriangleFunction(fuzzySetSP2, List.of(40.0, 60.0, 80.0), getYUtil.getY(List.of(40.0, 60.0, 80.0)));
        sp_fs.addMemberFunction(sp_mf2);
        IMemberFunction sp_mf3 = new TrapzoidFunction(fuzzySetSP3, List.of(70.0, 85.0, 100.0, 100.0), getYUtil.getY(List.of(70.0, 85.0, 100.0, 100.0)));
        sp_fs.addMemberFunction(sp_mf3);

        //Subject Difficulty
        String subjectDifficultyVar = "SubjectDifficulty"
         , fuzzySetSD1 = "easy" , fuzzySetSD2 = "moderate" , fuzzySetSD3 = "hard";
        FuzzySet sd_fs = new FuzzySet();

        IMemberFunction sd_mf1 = new TrapzoidFunction(fuzzySetSD1, List.of(1.0,1.0,3.0,5.0), getYUtil.getY(List.of(0.0, 0.0, 30.0, 50.0)));
        sd_fs.addMemberFunction(sd_mf1);

        IMemberFunction sd_mf2 = new TriangleFunction(fuzzySetSD2, List.of(4.0, 6.0, 8.0), getYUtil.getY(List.of(4.0, 6.0, 8.0)));
        sd_fs.addMemberFunction(sd_mf2);

        IMemberFunction sd_mf3 = new TrapzoidFunction(fuzzySetSD3, List.of(7.0, 9.0, 10.0, 10.0), getYUtil.getY(List.of(7.0, 9.0, 10.0, 10.0)));
        sd_fs.addMemberFunction(sd_mf3);

        // Sleep Quality
        String sleepQualityVar = "SleepQuality"
         , fuzzySetSQ1 = "poor" , fuzzySetSQ2 = "adequate" , fuzzySetSQ3 = "excellent";

        FuzzySet sq_fs = new FuzzySet();
        IMemberFunction sq_mf1 = new TrapzoidFunction(fuzzySetSQ1, List.of(0.0,0.0,3.0,5.0), getYUtil.getY(List.of(0.0,0.0,3.0,5.0)));
        sq_fs.addMemberFunction(sq_mf1);
        IMemberFunction sq_mf2 = new TriangleFunction(fuzzySetSQ2, List .of(4.0,6.0,8.0), getYUtil.getY(List.of(4.0,6.0,8.0)));
        sq_fs.addMemberFunction(sq_mf2);        
        IMemberFunction sq_mf3 = new TrapzoidFunction(fuzzySetSQ3, List.of(7.0,9.0,10.0,10.0), getYUtil.getY(List.of(7.0,9.0,10.0,10.0)));
        sq_fs.addMemberFunction(sq_mf3);


        // Rules (provided) through api editor

        // outputs stress level
        String stressLevelVar = "StressLevel"
         , fuzzySetSL1 = "low" , fuzzySetSL2 = "medium" , fuzzySetSL3 = "high";
        FuzzySet sl_fs = new FuzzySet();
        IMemberFunction sl_mf1 = new TrapzoidFunction(fuzzySetSL1, List.of(0.0,0.0,25.0,45.0), getYUtil.getY(List.of(0.0,0.0,25.0,45.0)));
        sl_fs.addMemberFunction(sl_mf1);
        IMemberFunction sl_mf2 = new TriangleFunction(fuzzySetSL2, List.of(30.0,50.0,70.0), getYUtil.getY(List.of(30.0,50.0,70.0)));
        sl_fs.addMemberFunction(sl_mf2);
        IMemberFunction sl_mf3 = new TrapzoidFunction(fuzzySetSL3, List.of(55.0,75.0,100.0,100.0), getYUtil.getY(List.of(55.0,75.0,100.0,100.0)));
        sl_fs.addMemberFunction(sl_mf3);
        List<IRule> rules = new ArrayList<>();
       




    }
}
