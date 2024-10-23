package nl.tudelft.jpacman.npc.ghost;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
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

class ClydeTest {
    private List<String> map;
    private GhostFactory ghost;
    private LevelFactory level;
    private GhostMapParser mapParser;
    private PacManSprites pcSprite;
    private BoardFactory board;


    @Test
    void Essai(){
        map = Arrays.asList("############", "#P        C#", "############");
        pcSprite = new PacManSprites();
        ghost = new GhostFactory(pcSprite);
        level = new LevelFactory(pcSprite, ghost);
        board = new BoardFactory(pcSprite);
        mapParser = new GhostMapParser(level, board, ghost);
        Ghost clyde = ghost.createClyde();
        Level mapfinal = mapParser.parseMap(map);
        clyde.nextAiMove();

    }

}
