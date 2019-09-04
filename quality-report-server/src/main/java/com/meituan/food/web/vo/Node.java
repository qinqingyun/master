package com.meituan.food.web.vo;

import java.util.List;

public class Node {
    public Node() { }

    public Node(String str, List<Node> node)
    {
        text = str;
        nodes = node;
    }

    public String text;

    public List<Node> nodes;
}
