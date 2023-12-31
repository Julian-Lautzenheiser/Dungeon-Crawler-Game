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
import com.example.myapplication.Models.ScorePowerUp;
import com.example.myapplication.ViewModels.AttackingViewModel;
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
    private Texture weapon;
    private String level = "room3.tmx";
    private Texture enemy1Sprite;
    private Texture enemy2Sprite;
    private Player player = Player.getInstance();
    private EnemyFactory enemies = new EnemyFactory();
    private Enemy skeletonEnemy = enemies.createEnemy("Skeleton");
    private Enemy demonEnemy = enemies.createEnemy("Demon");
    private MovementViewModel movement = new MovementViewModel();
    private AttackingViewModel attacking = new AttackingViewModel();
    private double score;
    private int playerHealth;
    private String nameDisplay;
    private String difficultyDisplay;
    private String scoreDisplay;
    private String healthDisplay;
    private BitmapFont statsDisplay;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private ScorePowerUp scorePowerup = new ScorePowerUp(player);
    private Texture scorePowerupSprite;
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

        scorePowerupSprite = new Texture(Gdx.files.internal("coin_anim_f0.png"));

        weapon = new Texture(Gdx.files.internal("sword.png"));

        map = new TmxMapLoader().load(level);
    
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        
        movement.addSubscriber(skeletonEnemy);
        movement.addSubscriber(demonEnemy);

        skeletonEnemy.setPositionX(158);
        skeletonEnemy.setPositionY(100);
        skeletonEnemy.setWidth(35);
        skeletonEnemy.setHeight(45);

        demonEnemy.setPositionX(258);
        demonEnemy.setPositionY(175);
        demonEnemy.setWidth(38);
        demonEnemy.setHeight(55);

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
        System.out.println(player.getPlayerX() + "" + player.getPlayerY());

        movement.updatePosition(level);
        skeletonEnemy.move(level);
        demonEnemy.move(level);

        attacking.checkAttack(movement.getEnemyList());

        game.getBatch().begin();
    
        statsDisplay.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        statsDisplay.draw(game.getBatch(), scoreDisplay, 25, 80);
        statsDisplay.draw(game.getBatch(), healthDisplay, 350, 80);
        statsDisplay.draw(game.getBatch(), nameDisplay, 25, 50);
        statsDisplay.draw(game.getBatch(), difficultyDisplay, 350, 50);
        
        game.getBatch().draw(sprite, player.getPlayerX(), player.getPlayerY(),
                player.getWidth(), player.getHeight());
        if (player.isAttacking()) {
            game.getBatch().draw(weapon, player.getPlayerX() + player.getWidth() - 16,
                    player.getPlayerY() + player.getHeight() / 4, 32, 16);
        }
        if (skeletonEnemy.getAlive()) {
            game.getBatch().draw(enemy1Sprite, skeletonEnemy.getPositionX(),
                    skeletonEnemy.getPositionY(),
                    skeletonEnemy.getWidth(), skeletonEnemy.getHeight());
        }
        if (demonEnemy.getAlive()) {
            game.getBatch().draw(enemy2Sprite, demonEnemy.getPositionX(),
                    demonEnemy.getPositionY(),
                    demonEnemy.getWidth(), demonEnemy.getHeight());
        }
        if (scorePowerup.isVisible()) {
            game.getBatch().draw(scorePowerupSprite, 360, 140, 32, 32);
        }

        game.getBatch().end();
    
        scoreDisplay = "Score: " + player.getScore();
        healthDisplay = "HP: " + player.getHealth();
        
        if (player.getHealth() <= 0) {
            game.setScreen(new LosingScreen(game));
            dispose();
        }
        
        if (movement.checkExit(level)) {
            game.setScreen(new WinningScreen(game));
            dispose();
        }

        if (movement.checkPowerup(level, scorePowerup.isVisible())) {
            scorePowerup.play();
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
        movement.removeSubscriber(demonEnemy);
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
