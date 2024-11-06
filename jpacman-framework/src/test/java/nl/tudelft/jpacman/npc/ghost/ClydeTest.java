package nl.tudelft.jpacman.npc.ghost;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ClydeTest {
    private List<String> map;
    private GhostFactory ghostFactory;
    private LevelFactory levelFactory;
    private GhostMapParser mapParser;

    private PlayerFactory playerFactory;
    private PacManSprites pcSprite;
    private BoardFactory board;

    @BeforeEach
    void setUp(){
        pcSprite = new PacManSprites();
        ghostFactory = new GhostFactory(pcSprite);
        levelFactory = new LevelFactory(pcSprite, ghostFactory);
        board = new BoardFactory(pcSprite);
        mapParser = new GhostMapParser(levelFactory, board, ghostFactory);
        playerFactory = new PlayerFactory(pcSprite);
    }


    @Test
    void testClydeFarFromPacman(){
        map = Arrays.asList("############", "#P        C#", "############");
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        Optional<Direction> nextMove = clyde.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.of(Direction.WEST));

    }

    @Test
    void testClydeNearPacman(){
        map = Arrays.asList("############", "#P      C  #", "############");
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        Optional<Direction> nextMove = clyde.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.of(Direction.EAST));
    }

    @Test
    void testClydeWhenNoPlayer(){
        map = Arrays.asList("############",
            "#       C  #",
            "############");

        Level level = mapParser.parseMap(map);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        Optional<Direction> nextMove = clyde.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.empty());
    }

    @Test
    void testClydeNotSameRow(){
        map = Arrays.asList("############",
            "#P         #",
            "#          #",
            "#          #",
            "##        C#",
            "############");
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        Optional<Direction> nextMove = clyde.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.of(Direction.NORTH)); //l
    }



}

