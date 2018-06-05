/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbl.ursa.mathematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcin7Cd
 */
public class Level {

    World world;
    List<Number> numbers;
    List<AddOperation> operations;
    Body boundary;
    Body boundaryLeft;
    Body boundaryRight;
    
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    Viewport viewport;
    Stage stage;
    SpriteBatch spriteBatch;
    
    public static final float PPM =20f;
    
    Level() {
        numbers = new ArrayList();
        operations = new ArrayList();

        world = new World(new Vector2(0, -10f), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        debugRenderer.render(world, camera.combined);
        viewport = new StretchViewport(320, 480, camera);
        stage = new Stage(viewport);
        
        
        world.setContactListener(new NumberContactListener(this));
        createFloor();
        createBoundary();
    }
    
    Camera getCamera(){
    return camera;
    }
    
    void addNewActor(Actor actor){
        stage.addActor(actor);
    }

    Number selectNumberAt(float x, float y) {
        Rectangle r1 = new Rectangle();
        for (Number currentNumber : numbers) {
            r1.setSize(currentNumber.dimension.x,currentNumber.dimension.y);
            r1.setCenter(currentNumber.realBody.getWorldCenter().cpy().scl(Level.PPM) );
            //Gdx.app.log("xN", Float.toString(x-currentNumber.position.x - currentNumber.dimension.x));
            //Gdx.app.log("yN", Float.toString(y-currentNumber.position.y - currentNumber.dimension.y));
            if (r1.contains(x, y)) {
                return currentNumber;
            }
        }
        return null;
    }

    void createFloor() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -1);

        boundary = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20, 1);

        boundary.createFixture(shape, 0.0f);
        shape.dispose();
    }
    
    void createBoundary() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-1, 10);
        boundaryLeft = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 20);
        boundaryLeft.createFixture(shape, 0.0f);
        shape.dispose();
        
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(320.0f/Level.PPM + 1.0f,10);
        boundaryRight = world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(1, 20);
        boundaryRight.createFixture(shape, 0.0f);
        
        shape.dispose();
        
    }

    void addNumberAt(float x, float y, int value) {
        numbers.add(new Number(x, y, value,world));
    }
    
    void removeNumber(Number number){
        number.remove();
        numbers.remove(number);
    }
    
    void replaceNumber(Number replaced, int value){
        replaced.remove();
        float x = replaced.getLogicalX();
        float y = replaced.getLogicalY();
        Vector2 velocity = replaced.realBody.getLinearVelocity();
        Number temp;
        if(numbers.remove(replaced)){
            temp = new Number(x,y,value,world);
            temp.realBody.setLinearVelocity(velocity);
            numbers.add(temp);
        }
    }
    
    boolean removeOperation(AddOperation operation){
        operation.stationaryNumber.deatachFromOperation();
        operation.movingNumber.deatachFromOperation();    
        //return operations.remove(operation);
        return true;
    }
    
    void appendAddOperation(Number stationaryNumber, Number movingNumber ){
        operations.add(new AddOperation(stationaryNumber,movingNumber,this));
        stationaryNumber.attachToOperation();
        movingNumber.attachToOperation();
        
    }

    void update(float dt) {
        world.step(dt, 3, 3);
        for (Number currentNumber : numbers) {
            currentNumber.update(dt);
        }
        for (AddOperation currentOperation : operations) {
            currentOperation.update(dt);
        }
        for(int i=0;i<operations.size();i++){
            if(operations.get(i).isToBeDestroyed){
                operations.remove(i);
                i--;
            }
        }
        stage.act();
    }

    void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Number currentNumber : numbers) {
            currentNumber.render(spriteBatch);
        }
        for (AddOperation currentOperation : operations) {
            currentOperation.render(spriteBatch);
        }
        spriteBatch.end();
        stage.draw();
    }
}
