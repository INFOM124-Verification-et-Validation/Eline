package com.gildedrose;

class GildedRose {
    Item[] items;
    static final String AGED_BRIE = "Aged Brie";
    static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].name.equals(AGED_BRIE)) {
                updateQualityAgedBrie(items[i]);
            } else if (items[i].name.equals(BACKSTAGE_PASSES)) {
                updateQualityBackstagePasses(items[i]);
            } else if (items[i].name.equals(SULFURAS)) {
                updateQualitySulfuras(items[i]);
            }
            else if (items[i].name.contains("Conjured")) {
                updateQualityConjured(items[i]);
            }
            else {
                updateQuality_Other(items[i]);
            }
        }
    }

    public void updateQualityAgedBrie(Item item){
        item.sellIn = item.sellIn - 1;

        if (item.quality < 50){
            item.quality = item.quality + 1;
        }
    }

    public void updateQualityBackstagePasses(Item item){
        item.sellIn = item.sellIn - 1;

        if (item.sellIn <= 0) {
            item.quality = 0;
        }

        if (item.sellIn < 6 && item.quality < 48) {
            item.quality = item.quality + 3 ;
        }
        else if(item.sellIn < 11 && item.quality < 49){
            item.quality = item.quality + 2;
        }
        else if(item.quality < 50){
            item.quality = item.quality + 1;
        }

    }

    public void updateQuality_Other(Item item){
        item.sellIn = item.sellIn - 1;

        if (item.sellIn > 0 && item.quality > 0) {
            item.quality = item.quality - 1;
        }

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 2;
        }
    }

    public void updateQualitySulfuras(Item item){
        // nothing to do
    }

    public void updateQualityConjured(Item item){
        item.sellIn = item.sellIn - 1;

        if (item.sellIn > 0 && item.quality > 1) {
            item.quality = item.quality - 2;
        }

        if (item.sellIn < 0 && item.quality > 3) {
            item.quality = item.quality - 4;
        }
    }

}
