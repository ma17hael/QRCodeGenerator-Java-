package Modele;

import java.awt.Color;

public class DonnéesPersonnalisation {
	private String titleFontName;
	private int titleFontSize;
	private boolean isTitleBold;
	private boolean isTitleItalic;
	private boolean isTitleUnderline;
	private Color titleColor;
	private String textFontName;
	private int textFontSize;
	private boolean isTextBold;
	private boolean isTextItalic;
	private boolean isTextUnderline;
	private Color textColor;
	
	public DonnéesPersonnalisation() {
		this.titleFontName = "Tahoma";
		this.titleFontSize = 15;
		this.isTitleBold = false;
		this.isTitleItalic = false;
		this.isTitleUnderline = false;
		this.textColor = Color.BLACK;
		this.textFontName = "Tahoma";
		this.textFontSize = 15;
		this.isTextBold = false;
		this.isTextItalic = false;
		this.isTextUnderline = false;
		this.textColor = Color.BLACK;
	}

	public String getTitleFontName() {
		return titleFontName;
	}
	public void setTitleFontName(String titleFontName) {
		this.titleFontName = titleFontName;
	}

	public int getTitleFontSize() {
		return titleFontSize;
	}
	public void setTitleFontSize(int titleFontSize) {
		this.titleFontSize = titleFontSize;
	}

	public boolean isTitleBold() {
		return isTitleBold;
	}
	public void setTitleBold(boolean isTitleBold) {
		this.isTitleBold = isTitleBold;
	}

	public boolean isTitleItalic() {
		return isTitleItalic;
	}
	public void setTitleItalic(boolean isTitleItalic) {
		this.isTitleItalic = isTitleItalic;
	}

	public boolean isTitleUnderline() {
		return isTitleUnderline;
	}
	public void setTitleUnderline(boolean isTitleUnderline) {
		this.isTitleUnderline = isTitleUnderline;
	}

	public Color getTitleColor() {
		return titleColor;
	}
	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public String getTextFontName() {
		return textFontName;
	}
	public void setTextFontName(String textFontName) {
		this.textFontName = textFontName;
	}

	public int getTextFontSize() {
		return textFontSize;
	}
	public void setTextFontSize(int textFontSize) {
		this.textFontSize = textFontSize;
	}

	public boolean isTextBold() {
		return isTextBold;
	}
	public void setTextBold(boolean isTextBold) {
		this.isTextBold = isTextBold;
	}

	public boolean isTextItalic() {
		return isTextItalic;
	}
	public void setTextItalic(boolean isTextItalic) {
		this.isTextItalic = isTextItalic;
	}

	public boolean isTextUnderline() {
		return isTextUnderline;
	}
	public void setTextUnderline(boolean isTextUnderline) {
		this.isTextUnderline = isTextUnderline;
	}

	public Color getTextColor() {
		return textColor;
	}
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
}
