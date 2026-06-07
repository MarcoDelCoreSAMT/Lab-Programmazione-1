package io.github.some_example_name;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Intro extends ScreenAdapter {
    private final SkeletrixIsland game;

    public Intro(SkeletrixIsland game) {
        this.game = game;
    }

    private SpriteBatch batch;

    private Texture home;
    private Texture logo;
    private Texture wait1;
    private Texture wait2;
    private Texture wait3;
    private Texture hubHead;

    private BitmapFont title;
    private BitmapFont waitF;
    private BitmapFont com;
    private BitmapFont loadF;

    private Music music;
    private Sound edlaugh;
    private Music waitM;

    private boolean laughPlayed = false;
    private boolean fase1 = false;
    private boolean fase2 = false;
    private boolean waitMStarted = false;  // <-- evita play() ogni frame

    private float time = 0f;
    private float introDuration = 6f;
    private float fadeToHomeDuration = 6f;
    private float exitAlpha = 1f;
    private float exitDuration = 2f;
    private float tLoad = 0f;

    // Fasi del gioco
    private enum Fase { INTRO, HOME, WAIT1, WAIT2, EXIT }
    private Fase fase = Fase.INTRO;

    // Per il lampeggio del testo "Premi SPAZIO"
    private float blinkTimer = 0f;
    private boolean blinkVisible = true;

    private float hubAngle = 0f;
    private TextureRegion hubFaceRegion;
    private Spin hubSpin = new Spin();

    @Override
    public void show() {
        batch = new SpriteBatch();

        home = new Texture("home.png");
        logo = new Texture("theman.png");
        wait1 = new Texture("wait1.png");
        wait2 = new Texture("wait2.png");
        wait3 = new Texture("wait3.png");
        hubHead = new Texture("hubHead.png");

        hubFaceRegion = new TextureRegion(hubHead);

        title = new BitmapFont();
        title.setColor(55/255f, 63/255f, 62/255f, 1);
        title.getData().setScale(2.8f);

        waitF = new BitmapFont();
        waitF.getData().setScale(2f);

        com = new BitmapFont();
        com.setColor(43/255f, 42/255f, 42/255f, 1);
        com.getData().setScale(1.8f);

        loadF = new BitmapFont();
        loadF.setColor(152/255f, 101/255f, 96/255f, 1);
        loadF.getData().setScale(1.6f);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/SkM(66).mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        waitM = Gdx.audio.newMusic(Gdx.files.internal("sounds/SkM(28).mp3"));
        waitM.setLooping(true);
        waitM.setVolume(0.2f);

        edlaugh = Gdx.audio.newSound(Gdx.files.internal("sounds/themanlaugh.ogg"));
    }

    @Override
    public void render(float delta) {
        time += delta;
        blinkTimer += delta;
        if (blinkTimer > 0.6f) {
            blinkVisible = !blinkVisible;
            blinkTimer = 0f;
        }

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        switch (fase) {

            case INTRO: {
                if (!fase1) { fase1 = true; }

                ScreenUtils.clear(20/255f, 19/255f, 19/255f, 1);

                float progress = time / introDuration;
                float logoAlpha = (float) Math.sin(Math.min(progress, 1f) * Math.PI);

                batch.setColor(1, 1, 1, logoAlpha);
                float w = 400, h = 200;
                batch.draw(logo, (1040 - w) / 2, (780 - h) / 2, w, h);

                if (!laughPlayed && time >= introDuration * 0.5f) {
                    edlaugh.play(0.08f);
                    laughPlayed = true;
                }

                // Passa a HOME automaticamente
                if (time >= introDuration) {
                    fase = Fase.HOME;
                    time = 0f;
                }
                break;
            }

            case HOME: {
                if (!fase2) { System.out.println("\nWelcome to the museum."); fase2 = true; }

                float t = Math.min(time / fadeToHomeDuration, 1f);

                batch.setColor(1, 1, 1, t);
                batch.draw(home, -180, 0, 1472, 780);

                title.draw(batch, "S k e l e t r i x   I s l a n d", 115, 640);

                // Testo lampeggiante solo dopo il fade-in
                if (t >= 0.6f && blinkVisible) {
                    title.getData().setScale(1.4f);
                    title.setColor(55/255f, 63/255f, 62/255f, t);
                    title.draw(batch, "Premi [ S P A Z I O ]  per iniziare", 380, 100);
                    title.getData().setScale(2.8f);
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    fase = Fase.WAIT1;
                    time = 0f;
                    music.stop();
                }
                break;
            }

            case WAIT1: {
                // Avvia waitM una sola volta
                if (!waitMStarted) {
                    waitM.play();
                    waitMStarted = true;
                }

                float t = Math.min((time - 1f) / 2f, 1f);

                batch.setColor(1, 1, 1, t);
                batch.draw(wait1, 0, 0, 1040, 780);

                waitF.setColor(68/255f, 68/255f, 88/255f, t);
                waitF.draw(batch, "Y o u  s h o u l d n ' t  b e  h e r e . . .\nY o u  a r e  n o t  a u t h o r i z e d  t o  a c c e s s  t h i s  a r e a .", 100, 680);

                // Passa a WAIT2 dopo 4 secondi o con SPAZIO
                if (time >= 4f && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    fase = Fase.WAIT2;
                    time = 0f;
                }
                break;
            }

            case WAIT2: {
                float tF = Math.min(time / 3f, 1f);

                batch.setColor(1, 1, 1, tF);
                batch.draw(wait2, 0, 0, 1040, 780);

                waitF.setColor(187/255f, 188/255f, 187/255f, 1);
                waitF.draw(batch, "S o m e o n e  h a s  a l r e a d y \nt a r g e t e d  y o u . . .\n\nL o o k  f o r  w h a t  h e  n e e d s\na n d  m a y b e  f a t e  w i l l  c h a n g e .", 450, 520);

                if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && time > 4f){
                    fase = Fase.EXIT;
                    time = 0f;
                }

                // Qui puoi aggiungere il lancio del gioco vero e proprio
                // es: if (tF >= 1f) { /* avvia Umberts */ }
                break;
            }

            case EXIT: {
                // Alpha scende da 1 a 0
                exitAlpha = Math.max(1f - (time - 3f / exitDuration), 0f);

                hubSpin.update(delta);

                float w = 350f, h = 350f;

                batch.setColor(1, 1, 1, 0.45f);
                batch.draw(
                    hubFaceRegion,
                    (1040 - w) / 2f,
                    (780 - h) / 2f,
                    w / 2f, h / 2f,
                    w, h,
                    1f, 1f,
                    hubSpin.getAngle(),
                    false
                );
                loadF.draw(batch, "C a r i c a m e n t o  . . ." , 40f, 700f);
                com.draw(batch, "C o m a n d i :  WASD (Movimento)  |  [SPACE] (Salto)  |  E (Interagisci)", 20, 50);

                // Fade-out audio
                waitM.setVolume(exitAlpha * 0.2f);

                // Disegna tutto con alpha calante
                batch.setColor(1, 1, 1, exitAlpha);
                batch.draw(wait3, 0, 0, 1040, 780);

                waitF.setColor(140/255f, 112/255f, 101/255f, exitAlpha);
                waitF.draw(batch, "D o  t h e  r i g h t  t h i n g . . .", 600, 100);

                // Quando il fade è finito, lancia il gioco
                if (exitAlpha <= 0f) {
                    waitM.stop();

                    float tLoad = Math.min((time - 1f) / 2f, 1f);

                    tLoad += time;

                    if (tLoad >= 15f){
                        ScreenUtils.clear(0,0,0,1);
                        game.setScreen(new LevelDemo(game));
                    }
                }
                break;
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        home.dispose();
        logo.dispose();
        wait1.dispose();
        wait2.dispose();
        wait3.dispose();
        title.dispose();
        waitF.dispose();
        waitM.dispose();
        music.dispose();
        edlaugh.dispose();
        hubHead.dispose();
    }
}

