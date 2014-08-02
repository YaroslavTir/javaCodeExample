package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.*;

/**
 * User: NKarataeva
 * Date: 11.07.12
 * Time: 20:03
 */
public class ConsultationHistoryPageDTO extends AbstractPageDTO {
    @Mapping(value = "consultations")
    private List<ConsultationElementDTO> elements = new ArrayList<ConsultationElementDTO>();

    public class ConsultationComparator implements Comparator<ConsultationElementDTO> {
        @Override
        public int compare(ConsultationElementDTO consultation1, ConsultationElementDTO consultation2) {
            List<ConsultationMessageDTO> messages1 = consultation1.getMessages();
            List<ConsultationMessageDTO> messages2 = consultation2.getMessages();
            if (!messages1.isEmpty() && !messages2.isEmpty()) {
                Date date1 = messages1.get(messages1.size() - 1).getSendDate();
                Date date2 = messages2.get(messages2.size() - 1).getSendDate();
                if (date1.after(date2))
                    return -1;
                else if (date1.before(date2))
                    return 1;
                else
                    return 0;
            }
            if (consultation1.getId() > consultation2.getId())
                return -1;
            else if (consultation1.getId() < consultation2.getId())
                return 1;
            else
                return 0;
        }
    }

    public void addElement(ConsultationElementDTO element) {
        elements.add(element);
    }

    public List<ConsultationElementDTO> getElements() {
        if (elements == null) {
            elements = new ArrayList<ConsultationElementDTO>();
        }
        Collections.sort(elements, new ConsultationComparator());
        return elements;
    }
}
