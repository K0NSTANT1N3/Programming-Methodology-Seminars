package assignments.breakout.classes;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import assignments.breakout.classes.game_entities.Button;
import assignments.breakout.variables.Var;

public class Lobby extends GraphicsProgram {
    private static Lobby lobby;
    private Button play;
    private Button credits;
    private Button back;
    private Button replay;

    /**
     * getter-setters
     */

    public static Lobby getInstance() {
        if (lobby == null) lobby = new Lobby();
        return lobby;
    }

    public Button getReplay() {
        return replay;
    }

    public void setReplay(Button replay) {
        this.replay = replay;
    }

    public Button getPlay() {
        return play;
    }

    public void setPlay(Button play) {
        this.play = play;
    }

    public Button getCredits() {
        return credits;
    }

    public void setCredits(Button credits) {
        this.credits = credits;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public void buttonConstr() {
        playButtonConstr();
        creditsButtonConstr();
        backButtonConstr();
    }

    private void playButtonConstr() {
        Button button = new Button("PLAY", Var.WIDTH * 3 / 4, Var.HEIGHT * 3 / 4 + 40);
        setPlay(button);
    }

    private void creditsButtonConstr() {
        Button button = new Button("CREDITS", Var.WIDTH * 3 / 4, Var.HEIGHT * 3 / 4 - 40);
        setCredits(button);
    }

    private void backButtonConstr() {
        Button button = new Button("BACK", Var.WIDTH * 3 / 4, Var.HEIGHT * 3 / 4 + 40);
        setBack(button);
    }


    public GImage addImage(String s) {
        GImage image = new GImage(s);
        image.setSize(Var.WIDTH, Var.HEIGHT);
        image.sendToBack();
        return image;
    }

}
