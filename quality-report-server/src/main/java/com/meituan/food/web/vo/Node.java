package com.meituan.food.web.vo;

import java.util.List;

public class Node {
    public Node() { }

    public Node(String str, List<Node> node)
    {
        text = str;
        nodes = node;
    }

//    @JSONField(name = "text")
    public String text;

//    @JSONField(name = "nodes")
    public List<Node> nodes;
}
