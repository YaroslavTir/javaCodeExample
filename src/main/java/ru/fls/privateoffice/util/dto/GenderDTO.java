package ru.fls.privateoffice.util.dto;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * User: NKarataeva
 * Date: 06.04.12
 * Time: 10:20
 */
public enum GenderDTO {
        GENDER_MALE("gender.male"),
        GENDER_FEMALE("gender.female");
        private final String value;

        GenderDTO(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static GenderDTO fromValue(String v) {
            for (GenderDTO c: GenderDTO.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
}
