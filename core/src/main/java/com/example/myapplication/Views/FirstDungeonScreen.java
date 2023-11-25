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

public class FirstDungeonScreen implements Screen {
    private final Dungeon game;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private Stage stage;
    private TiledMap map;
    private TiledMapRenderer renderer;
    private float unitScale = 1 / 32f;
    private OrthographicCamera camera;
    private Texture sprite;
    private Texture enemy1Sprite;
    private Texture enemy2Sprite;
    private Player player = Player.getInstance();
    private EnemyFactory enemies = new EnemyFactory();
    private Enemy skeletonEnemy = enemies.createEnemy("Skeleton");
    private Enemy goblinEnemy = enemies.createEnemy("Goblin");
    private MovementViewModel movement = new MovementViewModel();
    private String nameDisplay;
    private String difficultyDisplay;
    private String scoreDisplay;
    private String healthDisplay;
    private BitmapFont statsDisplay;
    private String level = "room1.tmx";
    public FirstDungeonScreen(final Dungeon game) {
        player.setPlayerX(-1);
        player.setPlayerY(-1);
        
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        float h = Gdx.graphics.getHeight();
        float w = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 14, 9);
        camera.update();
    
        skin = new Skin(Gdx.files.internal("plain-james-ui.json"));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("plain-james-ui.atlas")));

        //Creates the sprite and sets the width and height
        sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
        player.setHeight(2 * sprite.getHeight());
        player.setWidth(2 * sprite.getWidth());

        enemy1Sprite = new Texture(Gdx.files.internal("Skeleton.png"));
        enemy2Sprite = new Texture(Gdx.files.internal("Goblin.png"));
      
        map = new TmxMapLoader().load("room1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        //Create list of spawned Enemies to track position/health
        movement.addSubscriber(skeletonEnemy);
        movement.addSubscriber(goblinEnemy);

        skeletonEnemy.setPositionX(290);
        skeletonEnemy.setPositionY(120);
        skeletonEnemy.setWidth(35);
        skeletonEnemy.setHeight(45);

        goblinEnemy.setPositionX(158);
        goblinEnemy.setPositionY(180);
        goblinEnemy.setWidth(40);
        goblinEnemy.setHeight(50);

        createStyle();
        //        next = new TextButton("Next", style);
        //        next.getLabel().setFontScale(6, 3);
        //        next.setPosition(400, 50);
        //
        //
        //        next.addListener(new ClickListener() {
        //            @Override
        //            public void clicked(InputEvent event, float x, float y) {
        //                game.setScreen(new SecondDungeonScreen(game));
        //                dispose();
        //            }
        //        });
        //stage.addActor(next);
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

        movement.updatePosition(level);
    
        skeletonEnemy.move(level);
        goblinEnemy.move(level);
        
        game.getBatch().begin();

        //Check if player runs into enemy
        
        statsDisplay.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        statsDisplay.draw(game.getBatch(), scoreDisplay, 25, 80);
        statsDisplay.draw(game.getBatch(), healthDisplay, 350, 80);
        statsDisplay.draw(game.getBatch(), nameDisplay, 25, 50);
        statsDisplay.draw(game.getBatch(), difficultyDisplay, 350, 50);
        
        game.getBatch().draw(sprite, player.getPlayerX(), player.getPlayerY(), player.getWidth(), player.getHeight());
        game.getBatch().draw(enemy1Sprite, skeletonEnemy.getPositionX(),skeletonEnemy.getPositionY(), skeletonEnemy.getWidth(), skeletonEnemy.getHeight());
        game.getBatch().draw(enemy2Sprite, goblinEnemy.getPositionX(), goblinEnemy.getPositionY(), goblinEnemy.getHeight(), goblinEnemy.getHeight());
        
        game.getBatch().end();
        
        scoreDisplay = "Score " + player.getScore();
        
        healthDisplay = "HP: " + player.getHealth();
        
        if (player.getHealth() <= 0) {
            game.setScreen(new LosingScreen(game));
            dispose();
        }
        
        if (movement.checkExit(level)) {
            game.setScreen(new SecondDungeonScreen(game));
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
        movement.removeSubscriber(skeletonEnemy);
        movement.removeSubscriber(goblinEnemy);
    }
    
    public String chosenDifficulty(double difficulty) {
        if (difficulty == 0.5) {
            return "Easy";
        } else if (difficulty == 0.75) {
            return "Medium";
        } else if (difficulty == 1.0) {
            return "Hard";
        }
        return null;
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
        
        scoreDisplay = "Score: " + player.getScore();
        healthDisplay = "HP: " + player.getHealth();
        nameDisplay = "Username: " + player.getName();
        difficultyDisplay = "Difficulty: " + chosenDifficulty(player.getDifficulty());
        statsDisplay = new BitmapFont();
    }
}
