package algorithm.atguigu.adapter;

public class MediaAdapter implements MediaPlayer {

    private AdvancedMediaPlayer advancedMusicPlayer1;
    private AdvancedMediaPlayer advancedMusicPlayer2;

    public MediaAdapter(AdvancedMediaPlayer advancedMusicPlayer1, AdvancedMediaPlayer advancedMusicPlayer2){
        this.advancedMusicPlayer1 = new VlcPlayer();
        this.advancedMusicPlayer2 = new Mp4Player();
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer1.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer2.playMp4(fileName);
        }
    }
}