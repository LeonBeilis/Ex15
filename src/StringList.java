public class StringList {
    private CharNode _head;

    public StringList( ) {
        _head = null;
    }
    public StringList(CharNode node) {
        if(node == null) {
            _head = null;
        } else {
            _head=new CharNode(node.getData(),node.getValue(),null);
            for(CharNode ptr= node.getNext(),last=_head;
                ptr!= null; ptr= ptr.getNext())
            {
                last.setNext(new CharNode(ptr.getData(),ptr.getValue(),
                        ptr.getNext()));
                last = last.getNext();
            }
        }
    }

    /**
     * Constructor reciving String and building its list
     * allowed method of String class: charAt, length
     * todo: _head must contain in value times it repeats itself in row
     * @param s
     */
    public StringList (String s) {
        if(s == null) {
            _head = null;
        } else {
            _head = new CharNode( s.charAt(0), 1,null);
            if(s.length() <= 1){ // if one letter
                return;
            }
            CharNode tmp = _head;
            int nextCharIndex = 1;
            while ( nextCharIndex < s.length() ) {
                if(_head.getData() == s.charAt(nextCharIndex)) { //update value
                    _head.setValue( _head.getValue() + 1 );
                    nextCharIndex++;
                } else {
                    _head.setNext( new CharNode( s.charAt(nextCharIndex), 1,null) );
                    _head = _head.getNext();
                    nextCharIndex++;
                }
            }
            _head = tmp; //reset _head to first
        }
    }

    /**
     * Copy Constructor
     * todo: fix for loop, value is times the char counted in row..
     * @param other
     */
    public StringList (StringList other) {
        System.out.println("other: " + other);
//        String toString = other.toString();
//        return new StringList(toString);
        /*
        int otherLength = other.length();
        _head = new CharNode( other.charAt(0), 1,null); //node init
        if(otherLength <= 1){ // if one letter
            return;
        }
        CharNode tmp = _head;
        for(int i=1; i < otherLength; i++){
            _head.setNext( new CharNode( other.charAt(i), i,null) );
            _head = _head.getNext();
        }
        _head = tmp; //reset _head to first
        */
    }

    /**
     * return char by index
     * todo: check if while reach to null, what to do then
     * @param i
     * @return
     */
    public char charAt(int i) {
        CharNode keepFirst = _head;
        while (  (_head.getValue() != i) && _head != null ) {
            _head = _head.getNext();
        }
        char foundCharacter = _head.getData();
        _head = keepFirst;
        return foundCharacter;
    }

    /**
     * todo: test it, mostly need to do all over
     * @param str
     * @return
     */
    public StringList concat (StringList str) {
        CharNode _headTmp = _head;
        String listA = new String();
        while (  _head != null ) {
            listA += _head.getData();
            _head = _head.getNext();
        }
        _head = _headTmp;
        int strIndex = 0;
        int strLength = str.length();
        while ( strIndex < strLength ) {
            listA += str.charAt(strIndex);
            strIndex++;
        }
        StringList concatStringList = new StringList(listA);
        return concatStringList;
    }
    /*

    public int indexOf (int ch) {

    }

    public int indexOf (int ch, int fromIndex) {

    }

    public boolean equals (StringList str) {

    }

    public int compareTo (StringList str) {

    }

    public StringList substring(int i) {

    }

    public StringList substring(int i, int j) {

    }

    */

    public int length() {
        int count = 0;
        CharNode keepFirst = _head;
        while (  _head != null ) {
            _head = _head.getNext();
            count++;
        }
        _head = keepFirst;
        return count;
    }

    /**
     * todo: toString receving toString, fix "\"" issue
     * @return
     */
    public String toString() {
        CharNode keepFirst = _head;
        String r = "\"";
        while (_head != null){
            if(_head.getValue() > 1){
                int timesToRepeat = _head.getValue();
                timesToRepeat++;
                String tmp = "";
                for (int i = 1; i < timesToRepeat; i++){
                    tmp += _head.getData();
                }
                r += tmp;
            } else if(_head.getValue() == 1){
                r += _head.getData();
            }
            _head = _head.getNext();
        }
        _head = keepFirst;
        return r + "\"";
    }
}