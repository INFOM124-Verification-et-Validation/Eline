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


public class InkyTest {

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
    void testInkyFollowBlinky(){
        map = Arrays.asList("############",
                            "#P       BI#",
                            "#          #",
                            "############");
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Optional<Direction> nextMove = inky.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.of(Direction.WEST));

    }

    @Test
    void testBlinkyFarFromPacman(){
        map = Arrays.asList("############",
                            "#          #",
                            "#          #",
                            "#PI        #",
                            "#         B#",
                            "############");
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Optional<Direction> nextMove = inky.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.of(Direction.NORTH)); // Inky dessine la ligne en haut et à gauche

    }

    @Test
    void testInkyWhenNoBlinky() {
        List<String> map = Arrays.asList(
            "############",
            "#P        I#",
            "#          #",
            "#          #",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Optional<Direction> nextMove = inky.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.empty());
    }

    @Test
    void testInkyWhenNoPacMan() {
        List<String> map = Arrays.asList(
            "############",
            "#        I #",
            "#          #",
            "#          #",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Optional<Direction> nextMove = inky.nextAiMove();
        assertThat(nextMove).isEqualTo(Optional.empty());
    }

    @Test
    void testInkyWhenBlocked() {
        List<String> map = Arrays.asList(
            "############",
            "#P       #I#",
            "#         ##",
            "#          #",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Optional<Direction> nextMove = inky.nextAiMove();

        assertThat(nextMove).isEqualTo(Optional.empty());
    }



}
