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
import com.example.myapplication.Models.Enemy;
import com.example.myapplication.Models.Player;
import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.ViewModels.EnemyFactory;
import com.example.myapplication.ViewModels.MovementViewModel;

import java.util.ArrayList;
import java.util.List;

public class ThirdDungeonScreen implements Screen {
    private final Dungeon game;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private Stage stage;
    private TiledMap map;
    private TiledMapRenderer renderer;
    private float unitScale = 1 / 32f;
    private OrthographicCamera camera;
    private Texture sprite;
    private String level = "room3.tmx";
    private Texture enemy1Sprite;
    private Texture enemy2Sprite;
    private Player player = Player.getInstance();
    private EnemyFactory enemies = new EnemyFactory();
    private Enemy skeletonEnemy = enemies.createEnemy("Skeleton");
    private Enemy demonEnemy = enemies.createEnemy("Demon");
    private MovementViewModel movement = new MovementViewModel();
    private double score;
    private int playerHealth;
    private String scoreDisplay;
    private String healthDisplay;
    BitmapFont statsDisplay;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    public ThirdDungeonScreen(final Dungeon game) {
        //reset player position
        player.setPlayerX(-1);
        player.setPlayerY(-1);

        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 13, 9);
        camera.update();
    
        sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
        enemy1Sprite = new Texture(Gdx.files.internal("Skeleton.png"));
        enemy2Sprite = new Texture(Gdx.files.internal("Demon.png"));
    
        map = new TmxMapLoader().load(level);
    
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

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

        movement.updatePosition(level);
        
        skeletonEnemy.setPositionX(158);
        skeletonEnemy.setPositionY(100);
        
        demonEnemy.setPositionX(258);
        demonEnemy.setPositionY(185);
        
        game.getBatch().begin();
    
        statsDisplay.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        statsDisplay.draw(game.getBatch(), scoreDisplay, 25, 50);
        statsDisplay.draw(game.getBatch(), healthDisplay, 400, 50);
        
        game.getBatch().draw(sprite, player.getPlayerX(), player.getPlayerY(), player.getWidth(), player.getHeight());
        
        game.getBatch().draw(enemy1Sprite, skeletonEnemy.getPositionX(), skeletonEnemy.getPositionY(), 35, 45);
        game.getBatch().draw(enemy2Sprite, demonEnemy.getPositionX(), demonEnemy.getPositionY(), 38,55);
        game.getBatch().end();
    
        healthDisplay = "HP: " + player.getHealth();
    
    
        if (player.getHealth() <= 0) {
            game.setScreen(new LosingScreen(game));
            dispose();
        }
        
        if (movement.checkExit(level)) {
            game.setScreen(new WinningScreen(game));
            dispose();
        }

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
    
        score = player.getScore();
        playerHealth = player.getHealth();
        scoreDisplay = "Score: " + player.getScore();
        healthDisplay = "HP: " + player.getHealth();
        statsDisplay = new BitmapFont();
    }
}
