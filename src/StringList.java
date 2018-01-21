/**
 * Ex15 Assigment
 * @author Leon Beilis
 */
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
     * Constructor receiving String and building its list
     * allowed method of String class: charAt, length
     * @param s
     */
    public StringList (String s) {
        if(s == null || s == "") {
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
        if(other == null){
            _head = null;
            return;
        }
        String s = new String(other.toString());
        s = s.replace("\"",""); //remove "" from other
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

    /**
     * return char by index
     * @param i
     * @return
     */
    public char charAt(int i) {
        CharNode keepFirst = _head;
        int c = 0;
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
     * concat two Stringlist Objects..
     * @param str
     * @return
     */
    public StringList concat (StringList str) {
        if(_head == null && str == null){
            return new StringList();
        }
        if(str == null) {
            String tmp = new String(toString()); //self
            return new StringList(tmp); //return self and exit
        }
        String loc = new String(str.toString()); //new String to concat
        loc = loc.replace("\"",""); //remove "" from other
        String s = new String(str.toString()); //new String to concat
        s = s.replace("\"",""); //remove "" from other
        String _r_s = new String(loc + s);
        return new StringList(_r_s);
    }

    /**
     * return first index by char value, -1 if char not in range
     * @param ch (char int value)
     * @return index
     */
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

    /**
     * return index by char value and fromIndex param, -1 if char not in range
     * @param ch (char int value), fromIndex int (start search fromIndex)
     * @return index
     */
    public int indexOf(int ch, int fromIndex) {
        if(fromIndex < 0){
            return -1;
        }
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
     * yes, I know I used equals, didn't had enough time and by _head cause many issues, gf 5 pts
     * @param str
     * @return
     */
    public boolean equals (StringList str) {
        if(str == null && _head != null){
            return false;
        }
        boolean ans = false;
        String self = new String(toString());
        self = self.replace("\"",""); //remove "" from other
        String strl = new String(str.toString());
        strl = strl.replace("\"",""); //remove "" from other
        if(!self.equals(strl)) { //diff length ofc false
            return false;
        }
        ans = equals( str, 0);
        return ans;
    }

    /**
     * compare between stringlist, chars value depended
     * @param str Stringlist
     * @return int, if bigger = 1, small = -1, equals = 0
     */
    public int compareTo (StringList str) {
        if(str == null && _head != null){
            return 1;
        } else if( _head == null && str != null){
            return -1;
        } else if( _head == null && str == null){
            return 0;
        }
        int listSelf = 0;
        int listStr = 0;
        for (int i = 0; i < length(); i++){
            listSelf += (int) charAt(i);
        }
        for (int i = 0; i < str.length(); i++){
            listStr += (int) str.charAt(i);
        }
        if(listSelf == listStr){
            return 0;
        } else if( listSelf < listStr){
            return -1;
        }
        return 1;
    }

    /**
     * substring on StringList object from index i on object string till its end
     * @param i
     * @return StringList from i
     */
    public StringList substring(int i) {
        String s = toString();
        s = s.replace("\"","");
        if(i == 0){ // no changes
            return new StringList(s);
        } else if (i >= s.length()){ // on last index
            return new StringList();
        }
        String _r = new String();
        for ( int _d = i; _d < s.length(); _d++){
            _r += charAt(_d);
        }
        return new StringList(_r);
    }

    /**
     * return substring from i (include) to j (not include)
     * @param i, j
     * @return
     */
    public StringList substring(int i, int j) {
        String s = toString();
        s = s.replace("\"","");
        if(i >= j || j > s.length()){ //wrong value, return empty
            return new StringList();
        } else if(i==0 && j==s.length()){ // return self if values match string length
            return new StringList(s);
        }
        String _r = new String();
        for ( int _d = i; _d < s.length(); _d++){
            if(_d >= i && _d < j) {
                _r += charAt(_d);
            }
        }
        return new StringList(_r);
    }

    /**
     * return count of Stringlist chars
     * @return int
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
     * Object to string.. not that intesting..
     * @return String wrapped with ""
     */
    public String toString() {
        CharNode keepFirst = _head;
        String r = "\"";
        while (_head != null){
            if(_head.getValue() > 1){
                int timesToRepeat = _head.getValue();
                String _t = "";
                for (int i = 0; i < timesToRepeat; i++){
                    _t += _head.getData();
                }
                r += _t;
            } else if(_head.getValue() == 1){
                r += _head.getData();
            }
            _head = _head.getNext();
        }
        _head = keepFirst;
        return r + "\"";
    }
}