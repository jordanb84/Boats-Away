package com.ld.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public enum SoundPlayer {
	Happy("Happy.ogg", true), Intense("Intense.ogg", true);
	SoundPlayer(String path){
		this.SOUND = (Gdx.audio.newSound(Gdx.files.internal("assets/" + path)));
	}
	
	SoundPlayer(String path, boolean music){
		this.MUSIC = (Gdx.audio.newMusic(Gdx.files.internal("assets/" + path)));
	}
	
	public static void play(Music music){
		if(SoundPlayer.PLAYING_MUSIC != null){
			SoundPlayer.PLAYING_MUSIC.stop();
		}
		
		SoundPlayer.PLAYING_MUSIC = music;
		SoundPlayer.PLAYING_MUSIC.play();
		SoundPlayer.PLAYING_MUSIC.setLooping(true);
	}
	
	public Music MUSIC;
	public Sound SOUND;
	
	public static Music PLAYING_MUSIC;
	
}
