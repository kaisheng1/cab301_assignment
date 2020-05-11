package collection;


/**
 * Implemented the data structure as resizeable array
 */

public class MemberCollection {
    private Member[] members;
    private int size;

    public MemberCollection(){
        members = new Member[10];
        size = 0;
    }

    /**
     *
     * @return true if the collection is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     *
     * @return the collection's size
     */
    public int size(){
        return size;
    }

    /**
     *
     * @param username
     * @return true if there exists a member with the username
     */
    public boolean contains(String username){
        for (int i = 0; i < size; i++){
            if (members[i].getUsername() == username){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param username
     * @return the member if found, otherwise null
     */
    public Member find(String username){
        for (int i = 0; i < size; i++){
            if (members[i].getUsername().compareTo(username) == 0){
                return members[i];
            }
        }
        return null;
    }

    /**
     *
     * @param member
     * @return true if successfull added
     */
    public boolean add(Member member){
        //check if there is any duplicates
        if (contains(member.getUsername())){
            return false;
        }

        size++;
        if (size > members.length){
            Member[] temp = new Member[members.length + 10];
            for (int i = 0; i < members.length; i++){
                temp[i] = members[i];
            }
            members = temp;
        }
        members[size-1] = member;

        return true;
    }

    /**
     *
     * @param username
     * @return true if succesffully removed
     */
    public boolean remove(String username){
        boolean removed = false;

        for (int i = 0; i < size; i++){
            if (username == members[i].getUsername()){
                for (int j = i; j < size - 1; j++){
                    members[j] = members[j+1];

                }
                size--;
                removed = true;
            }
        }

        return removed;
    }

    /**
     *
     * @param index
     * @return the ith member
     */
    public Member get(int index){
        if (index > size) return null;
        return members[index];
    }



}
