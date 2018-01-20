public class CharNode {
    private char _data;
    private int _value;
    private CharNode _next;

    public CharNode(char c, int val, CharNode n) {
        _data = c;
        _value = val;
        _next = n;
    }

    public CharNode getNext( ) { return _next; }
    public void setNext(CharNode node){ _next = node; }
    public int getValue() { return _value; }
    public void setValue(int v) { _value = v; }
    public char getData() { return _data; }
    public void setData(char c) { _data = c; }

}