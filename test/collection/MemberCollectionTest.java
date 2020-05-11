package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberCollectionTest {

    public MemberCollection members;

    @BeforeEach
    public void init(){
        members = new MemberCollection();
    }

    @Test
    void isEmpty() {
        assertTrue(members.isEmpty());
    }

    @Test
    void size() {
        assertEquals(members.size(), 0);
    }

    @Test
    void contains() {
        assertFalse(members.contains("Fa"));
    }

    @Test
    void find() {
        assertNull(members.find("Fa"));
    }

    @Test
    void add() {
        assertTrue(members.add(new Member("Fa", "1234", false)));
        assertFalse(members.isEmpty());
        assertNotNull(members.find("Fa"));
        assertTrue(members.contains("Fa"));
    }

    @Test
    void addOverTenMember(){
        for (int i = 0; i < 19; i++){
            members.add(new Member("member" + i, "1234", false));
        }
        assertEquals(members.size() , 19);
        assertEquals(members.get(12).getUsername(), "member12");
    }

    @Test
    void remove() {
        members.add(new Member("fa", "1234", false));
        assertTrue(members.remove("fa"));
        assertFalse(members.remove("fa")); //it doesn't exist anymore
    }
}