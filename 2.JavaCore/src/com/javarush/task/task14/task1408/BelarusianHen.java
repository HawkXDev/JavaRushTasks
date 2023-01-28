package com.javarush.task.task14.task1408;

class BelarusianHen extends Hen {

    @Override
    int getCountOfEggsPerMonth() {
        return 250;
    }

    String getDescription() {
        return super.getDescription() + String.format(" Моя страна - %s. Я несу %d яиц в месяц.", Country.BELARUS, getCountOfEggsPerMonth());
    }
}
