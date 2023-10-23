package com.example.myapplication.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.ViewModels.MyTextInputListener;

public class ConfigScreen implements Screen {
    private final Dungeon game;
    private OrthographicCamera camera;
    private Table table;
    private Stage stage;
    private TextButton.TextButtonStyle style;
    private Skin skin;
    private TextButton spriteMage;
    private TextButton spriteKnight;
    private TextButton spriteFighter;
    private TextButton difficultyEasy;
    private TextButton difficultyMedium;
    private TextButton difficultyHard;
    private TextButton enterName;
    private TextButton play;
    private ButtonGroup<TextButton> difficulties;
    private ButtonGroup<TextButton> sprites;
    private MyTextInputListener listener = new MyTextInputListener();
    private Texture sprite;
    
    public ConfigScreen(final Dungeon game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 15);
        
        createStyle();
        
        createButtons();
        createButtonTable();
        
        stage.addActor(table);
        addListeners();
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
    
        game.getBatch().begin();
        game.getBatch().draw(sprite, 750, 500, 64, 64);
        game.getBatch().end();
        
        stage.draw();
        stage.act();
        
        // Updates the enterName's text to display player name
        if (game.getPlayerName() != null && game.getPlayerName() != "") {
            enterName.setText(game.getPlayerName());
        }
    }
    
    @Override
    public void show() {
    
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
        sprite.dispose();
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
    public void createButtonTable() {
        //Creates table with the different buttons and formats it
        table = new Table();
        //table.setTransform(true);
        table.add(enterName).colspan(5).width(830);
        table.row();
        table.add(spriteMage).width(400);
        table.add(difficultyEasy).padLeft(25).width(400);
        table.row();
        table.add(spriteKnight).width(400);
        table.add(difficultyMedium).padLeft(25).width(400);
        table.row();
        table.add(spriteFighter).width(400);
        table.add(difficultyHard).padLeft(25).width(400);
        table.row();
        table.add(play).colspan(5).width(830);
        
        table.setPosition(1150, 500);
    }
    public void createButtons() {
        //Creates sprite buttons
        spriteMage = new TextButton("Mage", style);
        spriteMage.getLabel().setFontScale(6, 4);
        spriteKnight = new TextButton("Knight", style);
        spriteKnight.getLabel().setFontScale(6, 4);
        spriteFighter = new TextButton("Fighter", style);
        spriteFighter.getLabel().setFontScale(6, 4);
        
        //Adds sprite buttons to a group for radio button functionality
        sprites = new ButtonGroup<>(spriteMage, spriteKnight, spriteFighter);
        sprites.setMaxCheckCount(1);
        //sprites.setMinCheckCount(0);
        sprites.setChecked("Mage");
        game.setSprite("Mage");
        spriteMage.setChecked(true);
        sprites.setUncheckLast(true);
        
        //Creates difficulty buttons
        difficultyEasy = new TextButton("Easy", style);
        difficultyEasy.getLabel().setFontScale(6, 3);
        difficultyMedium = new TextButton("Medium", style);
        difficultyMedium.getLabel().setFontScale(5, 3);
        difficultyHard = new TextButton("Hard", style);
        difficultyHard.getLabel().setFontScale(6, 3);
        
        //Adds difficulty buttons to a group for radio button functionality
        difficulties = new ButtonGroup<TextButton>(difficultyEasy,
                difficultyMedium, difficultyHard);
        difficulties.setMaxCheckCount(1);
        difficulties.setMinCheckCount(0);
        difficulties.setChecked("Easy");
        game.setDifficulty(0.5);
        difficulties.setUncheckLast(true);
        
        enterName = new TextButton("Enter Name", style);
        enterName.getLabel().setFontScale(6, 4);

        play = new TextButton("Play", style);
        play.getLabel().setFontScale(6, 4);
    
        sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
    }
    
    //Adds listeners all in one method so it's out of the constructor
    public void addListeners() {
        spriteMage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sprites.uncheckAll();
                sprites.setChecked("Mage");
                spriteMage.setChecked(true);
                game.setSprite("Mage");
                dispose();
                sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
            }
        });
        spriteKnight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sprites.uncheckAll();
                sprites.setChecked("Knight");
                spriteKnight.setChecked(true);
                game.setSprite("Knight");
                dispose();
                sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
            }
        });
        spriteFighter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sprites.uncheckAll();
                sprites.setChecked("Fighter");
                spriteFighter.setChecked(true);
                game.setSprite("Fighter");
                dispose();
                sprite = new Texture(Gdx.files.internal(game.getSprite() + ".png"));
            }
        });
        difficultyEasy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulties.uncheckAll();
                difficulties.setChecked("Easy");
                difficultyEasy.setChecked(true);
                game.setDifficulty(0.5);
            }
        });
        difficultyMedium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulties.uncheckAll();
                difficulties.setChecked("Medium");
                difficultyMedium.setChecked(true);
                game.setDifficulty(0.75);
            }
        });
        difficultyHard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulties.uncheckAll();
                difficulties.setChecked("Hard");
                difficultyHard.setChecked(true);
                game.setDifficulty(1.0);
            }
        });
        
        enterName.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.getTextInput(listener, "Input Your Name!",
                        "Input your name", "Your name");
            }
        });
        
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.getPlayerName() != null && game.getPlayerName() != "") {
                    game.setScreen(new FirstDungeonScreen(game));
                    dispose();
                }
            }
        });
    }
}