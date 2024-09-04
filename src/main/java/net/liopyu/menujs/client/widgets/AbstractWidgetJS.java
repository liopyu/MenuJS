package net.liopyu.menujs.client.widgets;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.liopyu.menujs.builders.widgets.AbstractWidgetBuilder;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AbstractWidgetJS extends AbstractWidget {

    private final AbstractWidgetBuilder builder;

    public AbstractWidgetJS(AbstractWidgetBuilder builder,int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.builder = builder;
    }

    public AbstractWidgetBuilder getBuilder() {
        return builder;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected ClientTooltipPositioner createTooltipPositioner() {
        return super.createTooltipPositioner();
    }

    @Override
    public void setTooltip(@Nullable Tooltip pTooltip) {
        super.setTooltip(pTooltip);
    }

    @Nullable
    @Override
    public Tooltip getTooltip() {
        return super.getTooltip();
    }

    @Override
    public void setTooltipDelay(int pTooltipMsDelay) {
        super.setTooltipDelay(pTooltipMsDelay);
    }

    @Override
    protected MutableComponent createNarrationMessage() {
        return super.createNarrationMessage();
    }

    @Override
    protected void renderScrollingString(GuiGraphics pGuiGraphics, Font pFont, int pWidth, int pColor) {
        super.renderScrollingString(pGuiGraphics, pFont, pWidth, pColor);
    }

    @Override
    public void renderTexture(GuiGraphics pGuiGraphics, ResourceLocation pTexture, int pX, int pY, int pUOffset, int pVOffset, int pTextureDifference, int pWidth, int pHeight, int pTextureWidth, int pTextureHeight) {
        super.renderTexture(pGuiGraphics, pTexture, pX, pY, pUOffset, pVOffset, pTextureDifference, pWidth, pHeight, pTextureWidth, pTextureHeight);
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        super.onClick(pMouseX, pMouseY);
    }

    @Override
    public void onRelease(double pMouseX, double pMouseY) {
        super.onRelease(pMouseX, pMouseY);
    }

    @Override
    protected void onDrag(double pMouseX, double pMouseY, double pDragX, double pDragY) {
        super.onDrag(pMouseX, pMouseY, pDragX, pDragY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    protected boolean isValidClickButton(int pButton) {
        return super.isValidClickButton(pButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    protected boolean clicked(double pMouseX, double pMouseY) {
        return super.clicked(pMouseX, pMouseY);
    }

    @Nullable
    @Override
    public ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        return super.nextFocusPath(pEvent);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return super.isMouseOver(pMouseX, pMouseY);
    }

    @Override
    public void playDownSound(SoundManager pHandler) {
        super.playDownSound(pHandler);
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public void setWidth(int pWidth) {
        super.setWidth(pWidth);
    }

    @Override
    public void setHeight(int value) {
        super.setHeight(value);
    }

    @Override
    public void setAlpha(float pAlpha) {
        super.setAlpha(pAlpha);
    }

    @Override
    public void setMessage(Component pMessage) {
        super.setMessage(pMessage);
    }

    @Override
    public Component getMessage() {
        return super.getMessage();
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public boolean isHoveredOrFocused() {
        return super.isHoveredOrFocused();
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void setFocused(boolean pFocused) {
        super.setFocused(pFocused);
    }

    @Override
    public int getFGColor() {
        return super.getFGColor();
    }

    @Override
    public void setFGColor(int color) {
        super.setFGColor(color);
    }

    @Override
    public void clearFGColor() {
        super.clearFGColor();
    }

    @Override
    public NarrationPriority narrationPriority() {
        return super.narrationPriority();
    }

    @Override
    protected void defaultButtonNarrationText(NarrationElementOutput pNarrationElementOutput) {
        super.defaultButtonNarrationText(pNarrationElementOutput);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int pX) {
        super.setX(pX);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int pY) {
        super.setY(pY);
    }

    @Override
    public void visitWidgets(Consumer<AbstractWidget> pConsumer) {
        super.visitWidgets(pConsumer);
    }

    @Override
    public ScreenRectangle getRectangle() {
        return super.getRectangle();
    }

    @Override
    public int getTabOrderGroup() {
        return super.getTabOrderGroup();
    }

    @Override
    public void setTabOrderGroup(int pTabOrderGroup) {
        super.setTabOrderGroup(pTabOrderGroup);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Nullable
    @Override
    public ComponentPath getCurrentFocusPath() {
        return super.getCurrentFocusPath();
    }

    @Override
    public void setPosition(int pX, int pY) {
        super.setPosition(pX, pY);
    }
}