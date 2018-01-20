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
     * @param other
     */
    public StringList (StringList other) {
        String s = new String(other.toString());
        s = s.replace("\"",""); //remove "" from other
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
     * return char by index
     * @param i
     * @return
     */
    public char charAt(int i) {
        CharNode keepFirst = _head;
        int c = 1;
        boolean flag = true;
        while (  _head != null && flag ) {
            if( _head.getValue() > 1 ) {
                int l = c;
                c += _head.getValue();
                if(i < c && i >= l){ //in range
                    flag = false;
                    break;
                } else {
                    _head = _head.getNext();
                }
            } else {
                if(i == c){
                    flag = false;
                    break;
                } else {
                    c++;
                    _head = _head.getNext();
                }
            }
        }
        char foundCharacter = _head.getData();
        _head = keepFirst;
        return foundCharacter;
    }

    /**
     * todo: test this method
     * @param str
     * @return
     */
    public StringList concat (StringList str) {
        //save local _head
        CharNode reset = _head;
        //new head
        CharNode _concatStart = _head;
        CharNode _concatFrom = _head;
        while (_head != null){
            if(_head.getNext() == null){
                break;
            }
            _head = _head.getNext();
        }
        _concatFrom = _head;
        _head = reset; //rest to first

        String s = new String(str.toString()); //new String to concat
        s = s.replace("\"",""); //remove "" from other

        if(s == null) {
            return new StringList(_concatStart); //return self and exit
        } else {
            _concatFrom.setNext(  new CharNode( s.charAt(0), 1,null) );
            _concatFrom = _concatFrom.getNext();
            if(s.length() <= 1){ // if one letter
                return new StringList(_concatStart);
            }
            int nextCharIndex = 1;
            while ( nextCharIndex < s.length() ) {
                if(_concatFrom.getData() == s.charAt(nextCharIndex)) { //update value
                    _concatFrom.setValue( _concatFrom.getValue() + 1 );
                    nextCharIndex++;
                } else {
                    _concatFrom.setNext( new CharNode( s.charAt(nextCharIndex), 1,null) );
                    _concatFrom = _concatFrom.getNext();
                    nextCharIndex++;
                }
            }
            _concatFrom = _concatStart;
            return new StringList(_concatFrom);
        }
    }

    public int indexOf(int ch) {
        CharNode _r = _head;
        int i = 0;
        while ( _head != null ) {
            if(ch == (int) _head.getData()) {
                _head = _r;
                return i;
            }
            if(_head.getValue() > 1) {
                i += _head.getValue();
            } else {
                i++;
            }
            _head = _head.getNext();
        }
        _head = _r;
        return -1;
    }

    public int indexOf(int ch, int fromIndex) {
        CharNode _r = _head;
        int i = 0;
        while ( _head != null ) {
            if(ch == (int) _head.getData()) {
                if(i >= fromIndex){
                    _head = _r;
                    return i;
                }
            }
            if(_head.getValue() > 1) {
                i += _head.getValue();
            } else {
                i++;
            }
            _head = _head.getNext();
        }
        _head = _r;
        return -1;
    }

    /**
     * recursive check if chars equals, increasing offset if does
     * @param str
     * @param _strOffset
     * @return
     */
    public boolean equals (StringList str, int _strOffset) {
        if( _strOffset >= str.length()){ //end
            return true;
        } else if( charAt(_strOffset) == str.charAt(_strOffset) ){
            _strOffset++;
            return equals(str, _strOffset);
        } else {
            return false;
        }
    }

    /**
     * check if self equal to str in recursive state
     * @param str
     * @return
     */
    public boolean equals (StringList str) {
        boolean ans = false;
        CharNode _head_save = _head;
        if(length() != str.length()) { //diff length ofc false
            return false;
        }
        ans = equals( str, 0);
        _head = _head_save;
        return ans;
    }

    /**
     * compare between stringlist value depended
     * self == str = 0 | self < str = -1 | self > str = 1;
     * @param str
     * @return int
     */
    public int compareTo (StringList str) {
        int listSelf = 0;
        int listStr = 0;
        for (int i = 1; i <= length(); i++){
            listSelf += (int) charAt(i);
        }
        for (int i = 1; i <= str.length(); i++){
            listStr += (int) str.charAt(i);
        }
        if(listSelf == listStr){
            return 0;
        } else if( listSelf < listStr){
            return -1;
        }
        return 1;
    }

    /*

    public StringList substring(int i) {

    }

    public StringList substring(int i, int j) {

    }

    */

    public int length() {
        int count = 0;
        CharNode keepFirst = _head;
        while (  _head != null ) {
            if(_head.getValue() > 1){
                count += _head.getValue();
            } else {
                count++;
            }
            _head = _head.getNext();
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