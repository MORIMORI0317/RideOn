package net.morimori.rideon;

import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;

public class MCVersionChecker {
	public static Minecraft Mc = Minecraft.getMinecraft();

	public static boolean isMoreVersion(String varsionIn) {

		String[] MCvas = Mc.getVersion().split(Pattern.quote("."));
		String[] Needvas = varsionIn.split(Pattern.quote("."));
		int nu = 0;
		for (String st : MCvas) {
			try {
				if (!(Integer.parseInt(st) >= Integer.parseInt(Needvas[nu])))
					return false;
			} catch (Exception e) {
			}
			nu++;
		}
		return true;
	}
}
