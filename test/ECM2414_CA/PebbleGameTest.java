/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import ecm1414_ca.listenerEvent.GameListener;
import ecm1414_ca.listenerEvent.PlayerEvent;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author 660050748, 660049985
 */
public class PebbleGameTest {
    private int[][] pebbleValues;
    private int players;
    private MockPlayer player;
    
    public PebbleGameTest() {
    }
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing class PebbleGame");
    }
    
    
    
    public void setUp(int players) {
        
        this.players = players;
        this.pebbleValues = new int[3][this.players * 11];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < this.players * 11; j++){
                Random random = new Random();
                int number = random.nextInt(30) + 1;
                this.pebbleValues[i][j] = number;
                
            }
        }
        
        this.player = new MockPlayer();
        
    }
    
    @After
    public void tearDown() {
        this.players = 0;
        this.pebbleValues = null;
        this.player = null;
    }
    
    /**
     * Test of startGame method, of class PebbleGame.
     * Tests the startGame method with 100 players.
     */
    @Test(timeout=10000)
    public void testStartGameOnePlayer() {
        setUp(1);
        PebbleGame pg = new PebbleGame(this.players, this.pebbleValues);
        pg.startGame();
        
    }
    
    /**
     * Test of startGame method, of class PebbleGame.
     * Tests the startGame method with 100 players.
     */
    @Test(timeout=10000)
    public void testStartGameTenPlayer() {
        setUp(10);
        ecm1414_ca.PebbleGame pg = new ecm1414_ca.PebbleGame(this.players, this.pebbleValues);
        pg.startGame();
        
    }
    
    /**
     * Test of startGame method, of class PebbleGame.
     * Tests the startGame method with 100 players.
     */
    @Test(timeout=10000)
    public void testStartGameFiftyPlayer() {
        setUp(50);
        ecm1414_ca.PebbleGame pg = new ecm1414_ca.PebbleGame(this.players, this.pebbleValues);
        pg.startGame();
        
    }
    
    /**
     * Test of startGame method, of class PebbleGame.
     * Tests the startGame method with 100 players.
     */
    @Test(timeout=10000)
    public void testStartGameHundredPlayer() {
        setUp(100);
        ecm1414_ca.PebbleGame pg = new ecm1414_ca.PebbleGame(this.players, this.pebbleValues);
        pg.startGame();
        
    }
    
    
    /**
     * Test of startGame method, of class PebbleGame.
     * Tests the startGame method with 100 players.
     */
    @Test(timeout=100000)
    public void testStartGameFiveHundredPlayer() {
        setUp(500);
        ecm1414_ca.PebbleGame pg = new ecm1414_ca.PebbleGame(this.players, this.pebbleValues);
        pg.startGame();
        
    }

   

    /**
     * Test of onPlayerAnnouncedWinEvent method, of class PebbleGame.
     * Uses a MockPlayerListener object to test if the onPlayerAnnouncedWin
     * method has been fired. Asserts that the int returned by getCounter 
     * is equal to 2.
     */
    @Test
    public void testOnPlayerAnnouncedWinEvent() {
        setUp(1);
        MockPlayerListener mock = new MockPlayerListener();
        this.player.registerPlayerListener(mock);
        this.player.fireAnnounceWinEvent();
        assertEquals(2, mock.getCounter());
        mock.resetCounter();
        
    }

    /**
     * Test of onPlayerFoundEmptyBagEvent method, of class PebbleGame.
     * Uses a MockPlayerListener object to test if the onPlayerFoundEmptyBag
     * method has been fired. Asserts that the int returned by getCounter 
     * is equal to 3.
     */
    @Test
    public void testOnPlayerFoundEmptyBagEvent() {
        setUp(1);
        MockPlayerListener mock = new MockPlayerListener();
        this.player.registerPlayerListener(mock);
        this.player.fireFoundEmptyBagEvent();
        assertEquals(3, mock.getCounter());
        mock.resetCounter();
        
    }

    /**
     * Test of onPlayerHasDrawnEvent method, of class PebbleGame.
     * Uses a MockPlayerListener object to test if the onPlayerHasDrawn
     * method has been fired. Asserts that the int returned by getCounter 
     * is equal to 1.
     */
    @Test
    public void testOnPlayerHasDrawnEvent() {
        setUp(1);
        MockPlayerListener mock = new MockPlayerListener();
        this.player.registerPlayerListener(mock);
        this.player.fireHasDrawnEvent();
        assertEquals(1, mock.getCounter());
        mock.resetCounter();
        
    }

   

    
    
}
