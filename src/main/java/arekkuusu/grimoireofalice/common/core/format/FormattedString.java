package arekkuusu.grimoireofalice.common.core.format;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A simple wrapper around a unlocalized string and formatting to accompany it.
 * Through the different factories, many different parameters can be passed
 * for different uses.
 * Formatting is generally passed in as a list of {@link TextFormatting}.
 * The unlocalized string is passed in with the arguments for
 * translating it afterwards.
 * A raw string can also be supplied which won't be translated.
 * Finally if the unlocalized strings need other arguments that themselves
 * need to be localized, these can be passed in with the
 * {@link FormattedString#withI18nArgs(String...)} method.
 */
public class FormattedString {

	private final String formatting;
	private final String unlocalizedString;
	private final String rawString;
	private final Object[] args;
	private final String[] i18nArgs;

	private FormattedString(String formatting, String unlocalizedString, String rawString, Object[] args, String[] i18nArgs) {
		this.formatting = formatting;
		this.unlocalizedString = unlocalizedString;
		this.rawString = rawString;
		this.args = args;
		this.i18nArgs = i18nArgs;
	}

	private FormattedString(List<TextFormatting> formatting, String unlocalizedString, String rawString, Object[] args, String[] i18nArgs) {
		this(formatting.stream().map(TextFormatting::toString).collect(Collectors.joining()), unlocalizedString, rawString, args, i18nArgs);
	}

	public static FormattedString of(String unlocalizedString, Object... args) {
		return new FormattedString(ImmutableList.of(), unlocalizedString, "", args, new String[0]);
	}

	public static FormattedString of(List<TextFormatting> formatting, String unlocalizedString, Object... args) {
		return new FormattedString(formatting, unlocalizedString, "", args, new String[0]);
	}

	public static FormattedString of(TextFormatting formatting, String unlocalizedString, Object... args) {
		return new FormattedString(ImmutableList.of(formatting), unlocalizedString, "", args, new String[0]);
	}

	public static FormattedString ofRaw(String rawString) {
		return new FormattedString(ImmutableList.of(), "", rawString, new Object[0], new String[0]);
	}

	public static FormattedString ofRaw(List<TextFormatting> formatting, String rawString) {
		return new FormattedString(formatting, "", rawString, new Object[0], new String[0]);
	}

	public static FormattedString ofRaw(TextFormatting formatting, String rawString) {
		return new FormattedString(ImmutableList.of(formatting), "", rawString, new Object[0], new String[0]);
	}

	public static FormattedString withItalics(String unlocalizedString, Object... args) {
		return of(TextFormatting.ITALIC, unlocalizedString, args);
	}

	public static FormattedString withItalics(TextFormatting formatting, String unlocalizedString, Object... args) {
		return withItalics(ImmutableList.of(formatting), unlocalizedString, args);
	}

	public static FormattedString withItalics(List<TextFormatting> formatting, String unlocalizedString, Object... args) {
		return of(ImmutableList.<TextFormatting>builder().add(TextFormatting.ITALIC).addAll(formatting).build(), unlocalizedString, args);
	}

	public FormattedString withI18nArgs(String... i18nArgs) {
		return new FormattedString(formatting, unlocalizedString, rawString, args, i18nArgs);
	}

	@SideOnly(Side.CLIENT)
	public String createFormattedString() {
		StringBuilder b = new StringBuilder(formatting);
		b.append(rawString);
		if(!unlocalizedString.isEmpty()) {
			List<String> translatedArgs = Arrays.stream(i18nArgs).map(I18n::format).collect(Collectors.toList());
			Object[] objects = ArrayUtils.addAll(args, translatedArgs.toArray());

			b.append(I18n.format(unlocalizedString, objects));
		}

		return b.toString();
	}
}
