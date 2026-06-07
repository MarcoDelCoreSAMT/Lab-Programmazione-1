package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
* Progetto LibGDX "Skeletrix Island"
 *
 * @author Marco Del Core
 * @version 7 giugno 2026
 */

public class LevelDemo extends ScreenAdapter {

    /**
    Si usa Viewport per impostare una risoluzione virtuale fissa: si preimposta una base e a dipendenza della finestra scala automaticamente
    In questo caso lo schermo sarà 1040x780 di risoluzione.
     */
    private OrthographicCamera camera;
    private Viewport viewport;

    private static final float VIRTUAL_W = 1040f;
    private static final float VIRTUAL_H = 780f;

    /**
    static final ==> variabile/costante globale
    static --> variabile appartiene classe e non a specifica istanza (oggetto). Crea una sola copia condivisa con programma.
    final --> variabile diventa immutabile. Assegno valore iniziale --> non può essere modificato o sovrascritto
     */

    // Colonne e righe nella sheet
    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 5;
    // Velocità animazione da fermo
    private static final float FRAME_DURATION = 0.1f;
    // Velocità movimento costante
    private static final float MOVE_SPEED = 200f;

    private SpriteBatch batch;

    // ==== Texture varie ====
    private Texture bgIm;
    private Texture bgIm2;
    private Texture nope;
    private Texture skullSR;
    private Array<SterlingRuby> skulls;
    private Texture uaif;
    private Texture jrPr;
    private Texture cr1;
    private Texture cr2;
    private Array<Scatola> scatole;
    private Texture inHere;
    private Texture endImg;

    // ==== Tutto riguardante Hubert ====
    private Texture idleSheet;
    private Texture walkSheet;
    private Texture ubJump;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;

    private Rectangle boundsHub;
    private Rectangle doorArea;

    // Scorre sempre così l'idle non riparte da zero ogni singola volta
    private float stateTime;
    private float x, y;
    private static final float HUB_W = 70f, HUB_H = 160f;

    private boolean isMoving;
    private boolean wasMoving;
    private boolean facingLeft;
    private float groundY;

    private float cameraX;
    private float bgWidth = 1040f;

    // ==== Oscillazione teschio ====
    private float skullT;
    private float time = 0f;

    // ==== Audio e condizioni esecuzione suono
    private Music bgAmbient;
    private Music skM1;

    private Sound step1;
    private Sound step2;
    private long step1Id = -1;
    private long step2Id = -1;
    private Sound umBreath;
    private long umBreathId = -1;
    private Sound coin;
    private Sound landJ;
    private Sound jump;


    // Conteggio teschi presi collidendo
    private int countSK;

    // Font bitmap
    BitmapFont huvFont;

    // Controllo se salta
    boolean isJumping = false;

    private float timeEnd = 0f;

    // Tentativo dell'utilizzo del R/W file... (basandosi su quello che ho fatto con .NET Maui)
    FileHandle fileSave= Gdx.files.local("salva.txt");

    // Impostazione delle schermate del gioco
    private final SkeletrixIsland game;

    public LevelDemo(SkeletrixIsland game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        // Package GL20 utilizzato per gestire trasparenza texture in png più accuratamente (proposto da AI)
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        camera = new OrthographicCamera();
        // FitViewport mantiene le proporzioni aggiungiendo delle bande nere ai lati dove necessario
        // Se non lo si vuole, si utilizza StretchViewport che estende tutta la finestra implicando però una distorzione delle immagini
        // Per evitare la distorzione e che non ci siano delle bande nere, si usa ExtendViewport
        viewport = new FitViewport(VIRTUAL_W, VIRTUAL_H, camera);
        viewport.apply();
        camera.position.set(VIRTUAL_W / 2f, VIRTUAL_H / 2f, 0);

        stateTime = 0f;

        x = 100f;
        y = 80f;

        groundY = 80f;
        countSK = 0;

        try {
            bgIm = new Texture("corridors.png");
            bgIm2 = new Texture("corridors2.png");
            nope = new Texture("nope.png");
            idleSheet = new Texture("staticUmberto.png");
            walkSheet = new Texture("camminUmberto.png");
            skullSR = new Texture("sRK.png");
            ubJump = new Texture("UbJump.png");
            uaif = new Texture("uaif.png");
            jrPr = new Texture("JrPr.png");
            cr1 = new Texture("cr1.png");
            cr2 = new Texture("cr2.png");
            inHere = new Texture("inHere.png");
            endImg = new Texture("End.jpg");
        } catch (Exception e) {
            Gdx.app.error("Assets", "Texture non trovata: " + e.getMessage());
        }

        skullSR.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skulls = GestoreLivelli.createSkulls();
        skullT = 0f;

        scatole = GestoreLivelli.createCrates(cr1, cr2);

        boundsHub = new Rectangle(x + 30f, y, HUB_W, HUB_H);

        doorArea = new Rectangle(1850f, 80f, 520f,240f);

        // Debug frame Hubert:
        // System.out.println("frameW=" + walkSheet.getWidth()/FRAME_COLS + " frameH=" + walkSheet.getHeight()/FRAME_ROWS);

        bgAmbient = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgAmbient.mp3"));
        skM1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/SkM1.mp3"));
        step1 = Gdx.audio.newSound(Gdx.files.internal("sounds/step1.mp3"));
        step2 = Gdx.audio.newSound(Gdx.files.internal("sounds/step2.mp3"));
        umBreath = Gdx.audio.newSound(Gdx.files.internal("sounds/umBreath.mp3"));
        coin = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.mp3"));
        landJ = Gdx.audio.newSound(Gdx.files.internal("sounds/landJ.mp3"));
        jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.mp3"));


        idleAnimation = buildAnimation(idleSheet);
        walkAnimation  = buildAnimation(walkSheet);

        huvFont = new BitmapFont();
        huvFont.getData().setScale(1.8f);

        bgAmbient.setLooping(true);
        bgAmbient.setVolume(0.18f);
        bgAmbient.play();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
    Ricostruisce e restituisce sheet in base a posizione griglia 5x5 e intervallo di tempo
    (Suggerito da claude AI)
     */
    private Animation<TextureRegion> buildAnimation(Texture sheet) {
        TextureRegion[][] tmp = TextureRegion.split(
            sheet,
            sheet.getWidth()  / FRAME_COLS,
            sheet.getHeight() / FRAME_ROWS
        );
        Array<TextureRegion> frames = new Array<>();
        for (int r = 0; r < FRAME_ROWS; r++)
            for (int c = 0; c < FRAME_COLS; c++)
                frames.add(tmp[r][c]);
        return new Animation<>(FRAME_DURATION, frames, Animation.PlayMode.LOOP);
    }

    /**
     * Gestione delle collisioni tra Hubert e un qualsiasi Collidable.
     * Permette di saltarci sopra o essere bloccato lateralmente.
     * NON ACCURATO: È MEGA DA SISTEMARE AIUTO
     */

    // Variabili locali per Forza Peso e salto
    float vYhub = 0f;
    float g = -981f;
    float FORZA_SALTO = 400f;

    private void handleCollision(Collidable obj){
        Rectangle cb = obj.getBounds();
        if (!boundsHub.overlaps(cb)){
            return;
        }

        float hubCenterX = boundsHub.x + boundsHub.width / 2f;
        float hubCenterY = boundsHub.y + boundsHub.height / 2f;
        float objCenterX = cb.x + cb.width / 2f;
        float objCenterY = cb.y + cb.height / 2f;

        float overlapX = (boundsHub.width + cb.width) / 2f - Math.abs(hubCenterX - objCenterX);
        float overlapY = (boundsHub.height + cb.height) / 2f - Math.abs(hubCenterY - objCenterY);

        if (overlapX < overlapY) {
            // Collisione laterale
            if (hubCenterX < objCenterX){
                x -= overlapX;
            } else {
                x += overlapX;
            }
            boundsHub.setPosition(x + 30f, y);
        } else {
            // Collisione verticale
            if (hubCenterY > objCenterY){
                // Hubert che si trova sopra la scatola atterra su quest'ultima
                y = cb.y + cb.height;   // posiziona esattamente sopra
                groundY = y;    // aggiorna pavimento
                vYhub = 0;
                isJumping = false;
                boundsHub.setPosition(x + 30f, y);
            } else {
                // Hubert scende verso il basso se no
                y = cb.y - boundsHub.height;
                boundsHub.setPosition(x + 30f, y);
                if (vYhub > 0f){
                    vYhub = 0f;
                }
            }
        }
    }

    // Check se è davanti alla porta della Jester's Privilege
    boolean isInFront = false;
    // Fa in modo che il tasto E possa essere premuto solo davanti all'entrata
    // Gestione fase del gioco
    private enum GameFase {PLAYING, FADING_OUT, END_SCREEN}
    private GameFase gameFase = GameFase.PLAYING;
    private float fadeOutTime = 0f;
    private static final float FADE_OUT_DURATION = 2f;

    @Override
    public void render(float delta) {
        // Impostazione della risoluzione viene prima di tutto
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Aggiungi fasi della intro

        if (gameFase == GameFase.PLAYING) {
            // --- Input ---
            float dx = 0f, dy = 0f;

            // Gestore degli imput della tastiera

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (!isJumping) {
                    groundY = y;
                    vYhub = FORZA_SALTO;
                    jump.play();
                    isJumping = true;
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                dx -= MOVE_SPEED * delta;
                facingLeft = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                dx += MOVE_SPEED * delta;
                facingLeft = false;
            }
            if (!isJumping && (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))) {
                dy += MOVE_SPEED * delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                dy -= MOVE_SPEED * delta;
            }

            isMoving = (dx != 0f || dy != 0f);

            if(isMoving){x += dx; y += dy;}
            stateTime += delta;
        }

        // Fa in modo che imamgine di background segua Hubert
        cameraX = x - Gdx.graphics.getWidth() / 2f;
        float areaWidth = bgWidth * 2f;
        cameraX = Math.max(0f, Math.min(cameraX, areaWidth - Gdx.graphics.getWidth()));

        // Debug posizione Hubert
        // System.out.println("x = " + x + "| y = " + y) ;

        // Fare in modo che salta e ritorna nella stessa posizione dove ha saltato, a meno che overlappa una Rect
        if (isJumping) {
            // gravità salto
            vYhub += g * delta;
            // aggiorno posizione
            y += vYhub * delta;

            if (y <= groundY) {
                y = groundY; // gravità rispettata anche sulle scatole
                vYhub = 0f;
                isJumping = false;
                landJ.play();
            }
        } else {
            groundY = 80f;
        }

        boundsHub.setPosition(x + 30f, y);

        if (isMoving && !wasMoving) {
            umBreath.stop(umBreathId);
            umBreathId = -1;

            step1Id = step1.loop(0.55f);
            step2Id = step2.loop(0.55f);
        } else if (!isMoving && wasMoving) {
            step1.stop(step1Id);
            step2.stop(step2Id);
            step1Id = -1;
            step2Id = -1;

            umBreathId = umBreath.loop(0.35f);
        }
        wasMoving = isMoving;

        // --- Animazione corrente: controlla che tipo di sheet fare visualizzare ---
        TextureRegion frame;

        Animation<TextureRegion> currentAnim = isMoving ? walkAnimation : idleAnimation;
        frame = currentAnim.getKeyFrame(stateTime);


        // --- Flip orizzontale per andare a sinistra ---
        // Le sprite guardano a destra di default: flippa se va a sinistra
        boolean shouldFlip = facingLeft;
        if (frame.isFlipX() != shouldFlip) {
            frame.flip(true, false);
        }

        // Transizione disegno da nero a colorato (alpha dell'opacità)
        time += delta;
        float t = 0;
        t = (time - 2f) / 2f;
        if (t > 1f) {
            t = 1f;
        }

        // Gestione collisioni area movimento (solo con controlli)
        if (x > bgWidth * 2 - 150f) {
            x = bgWidth * 2 - 150f;
        } else if (x < -40f) {
            x = -40f;
            if (y < 10f) {
                y = 10f;
            } else if (y > 120f && !isJumping) {
                y = 120f;
            }
        } else if (y < 10f){
            y = 10f;
        } else if (y > 120f && !isJumping){
            y = 120f;
        }

        /*
         * Foreach Casse + update posizione
         */

        for (Scatola scatola : scatole) {
            handleCollision(scatola);
            boundsHub.setPosition(x + 30f, y);
        }

        //Debug del frame
        //System.out.println("frameW=" + walkSheet.getWidth()/FRAME_COLS + " frameH=" + walkSheet.getHeight()/FRAME_ROWS);

        /**
         * Parte di logica della moneta sottoforma del teschio
         */
        skullT += delta;

        // Aggiorno collisioni Hubert per poter collidere con i teschi
        boundsHub.setPosition(x + 30f, y);

        for (SterlingRuby skull : skulls){
            if (!skull.isCollected()) {
                skull.update(skullT);

                if (boundsHub.overlaps(skull.getBounds())){
                    coin.play();
                    countSK++;
                    skull.collect();
                }
            }
        }

        // Rect porta: se va oltre mostra segno per entrare
        isInFront = boundsHub.overlaps(doorArea);

        // Trigger uscita con key E
        if (isInFront && Gdx.input.isKeyJustPressed(Input.Keys.E) && gameFase == GameFase.PLAYING){
            gameFase = GameFase.FADING_OUT;
            fadeOutTime = 0f;
            // Stop tutti audio, tranne il respiro di Hubert (mi sembrava figo)
            bgAmbient.stop();

            if(!isMoving) {
                umBreathId = umBreath.loop(0.35f);
            }

        }

        /*
        * === Parte di disegno, parte grafica del gioco (batch) ===
        */
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // === Fase di gioco generica ===
        if (gameFase == GameFase.PLAYING) {

            batch.setColor(1, 1, 1, t);
            batch.draw(bgIm, -cameraX, 0, bgWidth, Gdx.graphics.getHeight());
            batch.draw(bgIm2, -cameraX + bgWidth, 0, bgWidth, Gdx.graphics.getHeight());

            for (Scatola scatola : scatole) {
                scatola.draw(batch, cameraX);
            }

            batch.draw(nope, 905f - cameraX, 275f, 50f, 50f);

            for (SterlingRuby skull : skulls) {
                if (!skull.isCollected()) {
                    batch.draw(skullSR, skull.getX() - cameraX, skull.getY(), 45f, 55f);
                }
            }

            huvFont.setColor(190 / 255f, 175 / 255f, 147 / 255f, t);
            huvFont.draw(batch, "Colleziona tutti gli scheletri di Sterling Ruby:   " + countSK + " / " + GestoreLivelli.total(), 20f, 750f);

            if (isInFront) {
                batch.draw(inHere, 1962f - cameraX, 445f + (float) (Math.sin(skullT * 4.5f) * 3f), 40f, 35f);
            }

            batch.draw(jrPr, 1923f - cameraX, 375f, 122f, 55f);

            batch.draw(uaif, 255f - cameraX, 152f, -60f, 50f);
            batch.setColor(1, 1, 1, 1);

            if (isJumping && !facingLeft) {
                batch.draw(ubJump, x + 60f - cameraX, y, 135f, 220f);
            } else if (isJumping && facingLeft) {
                batch.draw(ubJump, x + 200f - cameraX, y, -135f, 220f);
            } else {
                batch.draw(frame, x - cameraX, y);
            }

        // === Fase di uscita del gioco (quando Hubert entra nella Jester's Privilege) ===
        } else if (gameFase == GameFase.FADING_OUT) {

            // Scrittura testo di salvataggio
            fileSave.writeString("Teschi raccolti: " + countSK, false);

            fadeOutTime += delta;
            float fadeAlpha = 1f - (fadeOutTime / FADE_OUT_DURATION);
            fadeAlpha = Math.max(fadeAlpha, 0f);

            // Disegna ancora ma con alpha in abbassamento
            batch.setColor(1, 1, 1, fadeAlpha);
            batch.draw(bgIm, -cameraX, 0f, bgWidth, Gdx.graphics.getHeight());
            batch.draw(bgIm2, -cameraX + bgWidth, 0f, bgWidth, Gdx.graphics.getHeight());
            batch.draw(jrPr, 1923f - cameraX, 375f, 122f, 55f);

            // Quando la transizione finisce, la fase cambia in quella attualmente conclusiva
            if (fadeOutTime >= FADE_OUT_DURATION) {
                gameFase = GameFase.END_SCREEN;
            }

        // === Fase conclusiva ===
        } else if (gameFase == GameFase.END_SCREEN){
            ScreenUtils.clear(0, 0, 0, 1);

            step1.stop();
            step2.stop();
            jump.stop();
            landJ.stop();
            umBreath.stop();

            skM1.setLooping(true);
            skM1.setVolume(0.18f);
            skM1.play();

            // Se il file di salvataggio esiste, legge il contenuto
            if (fileSave.exists()) {
                String data = fileSave.readString();
            }

            timeEnd += delta;

            float tEnd = (timeEnd - 3f) / 3f;
            if (tEnd < 0f) {
                tEnd = 0f;
            } else if (tEnd > 1f) {
                tEnd = 1f;
            }

            batch.setColor(1, 1, 1, tEnd);
            batch.draw(endImg, -cameraX + bgWidth, 0, bgWidth, Gdx.graphics.getHeight());
            huvFont.setColor(190 / 255f, 175 / 255f, 147 / 255f, 1);
            huvFont.getData().setScale(2.25f);
            huvFont.draw(batch, "Sterling Ruby raccolti: " + countSK + " / " + GestoreLivelli.total(), 320f, 450f);
            huvFont.getData().setScale(1.5f);
            batch.setColor(1, 1, 1, 1);
            huvFont.draw(batch, "G i o c o  c o m p l e t o  i n  a r r i v o . . .", 325f, 350f);
            huvFont.getData().setScale(1.6f); // reset alla scala originale
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        idleSheet.dispose();
        walkSheet.dispose();
        skullSR.dispose();
        bgIm.dispose();
        bgIm2.dispose();
        nope.dispose();
        bgAmbient.dispose();
        step1.dispose();
        step2.dispose();
        umBreath.dispose();
        coin.dispose();
        huvFont.dispose();
        ubJump.dispose();
        uaif.dispose();
        jrPr.dispose();
        cr1.dispose();
        cr2.dispose();
        jump.dispose();
        inHere.dispose();
        endImg.dispose();
        skM1.dispose();
    }
}
