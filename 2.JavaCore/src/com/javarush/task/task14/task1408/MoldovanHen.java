package com.javarush.task.task14.task1408;

class MoldovanHen extends Hen {

    @Override
    int getCountOfEggsPerMonth() {
        return 200;
    }

    String getDescription() {
        return super.getDescription() + String.format(" Моя страна - %s. Я несу %d яиц в месяц.", Country.MOLDOVA, getCountOfEggsPerMonth());
    }
}