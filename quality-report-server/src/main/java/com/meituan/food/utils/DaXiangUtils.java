package com.meituan.food.utils;

import com.alibaba.fastjson.JSONObject;
import com.sankuai.xm.pub.push.Pusher;
import com.sankuai.xm.pub.push.PusherBuilder;

public class DaXiangUtils {

    private static final Pusher ROOM_PUSHER = PusherBuilder
            .defaultBuilder()
            .withAppkey("4201005481118L01")
            .withApptoken("269ae042f01e1793314057c4853e757f")
            .withTargetUrl("https://xmapi.vip.sankuai.com/api/pub/pushToRoom")
            .withFromName("虚拟小组")
            .withToAppid((short) 1)
            .withFromUid(137442102174L)
            .build();

    private static final Pusher PERSON_PUSHER = PusherBuilder
            .defaultBuilder()
            .withAppkey("4201005481118L01")
            .withApptoken("269ae042f01e1793314057c4853e757f")
            .withTargetUrl("https://xmapi.vip.sankuai.com/api/pub/push")
            .withFromName("虚拟小组")
            .withToAppid((short) 1)
            .withFromUid(137442102174L)
            .build();

    public static boolean pushToRoom(String text, long roomId) {
        JSONObject jsonObject = ROOM_PUSHER.pushToRoom(text, roomId);
        long resultCode = jsonObject.getLongValue("rescode");
        return resultCode == 0;
    }

    public static boolean pushToPerson(String text, String... receivers) {
        JSONObject jsonObject = PERSON_PUSHER.push(text, receivers);
        long resultCode = jsonObject.getLongValue("rescode");
        return resultCode == 0;
    }

    private DaXiangUtils() {
    }


    public static void main(String[] args) {
        DaXiangUtils.pushToPerson("test","guomengyao");
        DaXiangUtils.pushToRoom("test",64012858900l);
    }
}
