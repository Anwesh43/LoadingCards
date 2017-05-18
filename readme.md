## LoadingCards

### Android library to create cards in your screen. This cards before being loaded into the screen shows an animation which will trick the user to believe that data is loaded quickly.


### Usage

#### When you create a card it consists of Bitmap, title and subtitle. It may contain some extra things but this are the basic need. Now to create a list of cards you have to use LoadingCardList. To add a card you have to can code the logic in fetchBitmap,fetchTitle,fetchSubTitle.

#### Creating LoadingCardList in Activity

```
    LoadingCardList loadingCardList = new LoadingCardList(this);
```

#### adding loading card along with the processor.

```
    loadingCardList.addLoadingCard(new CardProcessor(){
        public Bitmap fetchBitmap() {
            //Your blocking logic
            return null;
        }
        public String fetchTitle() {
          //Your blocking logic
          return null;
        }
        public String fetchSubTitle() {
          //Your blocking logic
          return null;
        }
    })
```

#### Showing loading card

```   
    loadingCardList.show();
```

#### Demo

<img src="https://github.com/Anwesh43/LoadingCards/blob/master/demo/loadingcards.gif" width="300px" height="500px">
