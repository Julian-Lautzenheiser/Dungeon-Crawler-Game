package com.example.myapplication.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.myapplication.Models.Player;
import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.ViewModels.MovementViewModel;

public class ThirdDungeonScreen implements Screen {
    private final Dungeon game;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private Stage stage;
    private TextButton next;
    private TiledMap map;
    private TiledMapRenderer renderer;
    private float unitScale = 1 / 32f;
    private OrthographicCamera camera;
    private Texture sprite;
    private Texture enemy1Sprite;
    private Texture enemy2Sprite;
    private Texture enemy3Sprite;
    private Player player = Player.getInstance();
    private MovementViewModel movement = new MovementViewModel();
    public ThirdDungeonScreen(final Dungeon game) {
        //reset player position
        player.setPlayerX(-1);
        player.setPlayerY(-1);

        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 13, 8);
        camera.update();
        
        /*
        String name = player.getName();
        int health = player.getHealth();
        double score = player.getScore();
        String difficulty = chosenDifficulty(player.getDifficulty());
         */
    
        sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
        enemy1Sprite = new Texture(Gdx.files.internal("Skeleton.png"));
        enemy2Sprite = new Texture(Gdx.files.internal("Ogre.png"));
        enemy3Sprite = new Texture(Gdx.files.internal("Demon.png"));
    
        map = new TmxMapLoader().load("room3.tmx");
    
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        /*
        Label nameDisplay = new Label("Player: " + name, skin);
        nameDisplay.setFontScale(2, 2);
        nameDisplay.setColor(Color.WHITE);
        Label hp = new Label("HP: " + health, skin);
        hp.setFontScale(2, 2);
        hp.setColor(Color.WHITE);
        scoreDisplay = new Label("Score: " + score, skin);
        scoreDisplay.setFontScale(2, 2);
        scoreDisplay.setColor(Color.WHITE);
        Label difficultyDisplay = new Label("Difficulty: " + difficulty, skin);
        difficultyDisplay.setFontScale(2, 2);
        difficultyDisplay.setColor(Color.WHITE);
    
        Table table = new Table();
        table.add(nameDisplay);
        table.row();
        table.add(hp);
        table.row();
    
        table.row();
        table.add(difficultyDisplay);
        table.row();
    
        table.setPosition(250, 100);
     */

        createStyle();
        //        next = new TextButton("Next", style);
        //        next.getLabel().setFontScale(6, 3);
        //        next.setPosition(400, 50);
        //
        //        next.addListener(new ClickListener() {
        //            @Override
        //            public void clicked(InputEvent event, float x, float y) {
        //                game.setScreen(new LeaderboardScreen(game));
        //                dispose();
        //            }
        //        });
        //
        //        stage.addActor(next);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        /*
        timeSeconds += Gdx.graphics.getRawDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            game.decreaseScore();
            scoreDisplay.setText("Score: " + player.getScore());
        }
        */
        game.getBatch().begin();
        game.getBatch().draw(sprite, player.getPlayerX(), player.getPlayerY() - 5, 64, 64);
        game.getBatch().draw(enemy1Sprite, 300, 250, 64, 64);
        game.getBatch().draw(enemy2Sprite, 250, 400, 64, 64);
        game.getBatch().draw(enemy3Sprite, 450, 550, 64, 64);
        movement.updatePosition("room3.tmx");
    
        if (player.getHealth() == 0) {
            game.setScreen(new LosingScreen(game));
            dispose();
        }
        
        if (movement.checkExit(player.getPlayerX(), player.getPlayerY(), "room3.tmx")) {
            game.setScreen(new WinningScreen(game));
            dispose();
        }

        game.getBatch().end();

        stage.draw();
        stage.act();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        sprite.dispose();
        enemy1Sprite.dispose();
        enemy2Sprite.dispose();
        enemy3Sprite.dispose();
    }
    
    public void createStyle() {
        //Creates the style to set how the buttons look
        style = new TextButton.TextButtonStyle();
        skin = new Skin();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        game.setButtonAtlas(new TextureAtlas(Gdx.files.internal("buttons.atlas")));
        skin.addRegions(game.getButtonAtlas());
        style.up = skin.getDrawable("button_up");
        style.down = skin.getDrawable("button_down");
        style.checked = skin.getDrawable("button_checked");
    }
}
