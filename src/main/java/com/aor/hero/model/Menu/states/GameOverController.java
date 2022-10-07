package com.aor.hero.model.Menu.states;

import com.aor.hero.Constants;
import com.aor.hero.controller.Controller;
import com.aor.hero.viewer.screens.GameOverScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOverController implements StateController, KeyListener {
    private final Controller context;
    private GameOverScreen screenView;

    private String nickNameSpaces;
    private String nickName;
    private static final int LIMITCHAR = 11;
    private Integer newScore;
    List<Integer> score;
    List<String> name;

    public GameOverController(Controller context){
        this.context = context;
        screenView = new GameOverScreen();
        nickNameSpaces = "_ _ _ _ _ _ _ _ _ _ _";
        nickName = "";
        newScore = 0;
        score = new ArrayList<>();
        name = new ArrayList<>();
    }
    public GameOverScreen getScreenView() {
        return screenView;
    }
    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);


        while (context.getApplicationState() == ApplicationState.GameOver) {
            getScreenView().draw();
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    @Override
    public void nextState() {
        context.changeState(ApplicationState.Menu);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nextState();
        }
        boolean isNumber = e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9;
        boolean isLetterMin = e.getKeyCode() > 96 && e.getKeyCode() < 123;
        boolean isCapitalLetter = e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z;

        if (isLetterMin || isCapitalLetter || isNumber)
            if(KeyEvent.getKeyText(e.getKeyCode()).length() == 1)
                writeName(e.getKeyChar());

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            updateLeaderboard(Constants.LEADERBOARD_FILE);
            nextState();
        }
    }

    public void updateLeaderboard(String path) {
        int line = 0;
        int newRank = 1;
        try {
            File myObj = new File(Constants.ROOT+path);
            Scanner myReader = new Scanner(myObj, Charset.defaultCharset().name());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                line++;
                if(line >= 3 && line <= 12){  //lines of leaderboardDraw.txt with the score
                    Integer lineScore = Integer.parseInt(data.substring(26,31));
                    score.add(lineScore);
                    if(newScore <= lineScore)
                        newRank = line-1;
                    name.add(data.substring(8,26));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        }
        addToLeaderBoard(newRank);
        writeLeaderboard(path);
    }

    private void addToLeaderBoard(int newRank){
        if(newRank >= 0 && newRank < 10) { // se ficar nos 10 melhores
            score.add(newRank-1, newScore);
            name.add(newRank-1,nickName + " ".repeat(Math.max(0, 18 - nickName.length())));
            score.remove(10);
            name.remove(10);
        }
    }

    public void writeLeaderboard(String path) {
        try {
            FileWriter myWriter = new FileWriter(Constants.ROOT+path, Charset.defaultCharset());
            myWriter.write("======L E A D E R B O A R D=======\n");
            myWriter.write("||                              ||\n");

            for(int i = 0; i < score.toArray().length; i++){
                String row;
                if(i == 9)
                    row = "|| " + (i + 1) + " - " + name.get(i) + formatScore(score.get(i)) + " ||\n";
                else
                    row = "|| " + (i + 1) + "  - " + name.get(i) + formatScore(score.get(i)) + " ||\n";
                myWriter.write(row);
            }
            myWriter.write("||                              ||\n");
            myWriter.write("||    PRESS ESC TO GO BACK      ||\n");
            myWriter.write("==================================\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String formatScore(Integer scr) {
        StringBuilder formated = new StringBuilder(Integer.toString(scr));
        for(int i = 0; i < 5 - Integer.toString(scr).length();i++)
            formated.insert(0, "0");
        return formated.toString();
    }

    public void writeName(char c) {
        if(nickName.length() + 1 <= LIMITCHAR) nickName += c;
        nickNameSpaces = "";
        for (char ch : nickName.toCharArray()) {
            nickNameSpaces += ch + " ";
        }
        for (int i = 0; i < (LIMITCHAR - nickName.length()); i++)
            nickNameSpaces += "_ ";
        getScreenView().setNickNameSpaces(nickNameSpaces);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void setScore(int score){
        getScreenView().setScore(score);
        this.newScore = score;
    }

    public String getNickName() {
        return nickName;
    }

    public String getNickNameSpaces() {
        return nickNameSpaces;
    }

    public List<Integer> getScore() {
        return score;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public void setScoreList(List<Integer> score) {
        this.score = score;
    }

    public void setScreenView(GameOverScreen screenView) {
        this.screenView = screenView;
    }
}