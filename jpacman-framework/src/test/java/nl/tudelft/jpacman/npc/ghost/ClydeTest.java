package nl.tudelft.jpacman.npc.ghost;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import nl.tudelft.jpacman.npc.ghost.Clyde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClydeTest {
    private List<String> map;
    private Clyde clyde;


    @Test
    void Essai(){
        map = Arrays.asList("############", "#P        C#", "############");
        clyde = new Clyde();

    }

}
