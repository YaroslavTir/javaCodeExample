package ru.fls.privateoffice.util.dto;

import com.google.gson.reflect.TypeToken;
import org.dozer.Mapping;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: NKarataeva
 * Date: 06.04.12
 * Time: 10:04
 */
public class ProfileElementDTO extends AbstractPageDTO {

    private String accountStatus;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    private String surname;
    private String givenName;
    private boolean transliterationMismatch;
    protected boolean swindler;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String citizenship;
    @NotNull
    private Date birthday;
    @NotNull
    private String birthplace;
    @NotNull
    private String codeWord;
    private boolean mailer;
    private boolean confirm;
    private String securityQuestion;
    private String securityAnswer;
    private String homePage;
    private String job;
    private String position;
    private String infoSource;
    private String clientInfoSource;
    protected PartnerElement registrationPartner;
    protected List<CobrandingElement> cobradings;
    private String cardInfo;
    protected Date lastAccountOperationDate;
    protected String additionStatus;
    protected String currentLevel;

    private String lang;

    @Mapping(value = "gender")
    @NotNull
    private GenderDTO sex;

    @Mapping(value = "addr")
    @NotNull
    private AddressDTO address;

    private String addressVtb;

    @Mapping(value = "document")
    private List<DocumentElementDTO> documents = new ArrayList<DocumentElementDTO>();

    @Mapping(value = "program")
    private List<ProgramElementDTO> programs = new ArrayList<ProgramElementDTO>();

    private SecretBlockDTO secretBlock;


    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setDocumentsJson(String docs) {
        java.lang.reflect.Type collectionType = new TypeToken<List<DocumentElementDTO>>() {
        }.getType();
        documents = getGson().fromJson(docs, collectionType);

        return;
    }

    public void setProgramsJson(String progs) {
        List<ProgramElementDTO> programs = new ArrayList<ProgramElementDTO>();
        if (progs != null) {
            String[] list = progs.split(",");
            for (String p : list) {
                ProgramElementDTO element = new ProgramElementDTO();
                element.setProgramType(p);
                programs.add(element);
            }
        }
        this.programs = programs;
        return;
    }


    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public SecretBlockDTO getSecretBlock() {
        return secretBlock;
    }

    public void setSecretBlock(SecretBlockDTO secretBlock) {
        this.secretBlock = secretBlock;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public boolean isTransliterationMismatch() {
        return transliterationMismatch;
    }

    public void setTransliterationMismatch(boolean transliterationMismatch) {
        this.transliterationMismatch = transliterationMismatch;
    }

    public boolean isSwindler() {
        return swindler;
    }

    public void setSwindler(boolean swindler) {
        this.swindler = swindler;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneString() {
        if (this.phone == null) {
            return null;
        }
        return this.phone.substring(1);
    }

    public void setPhoneString(String phone) {
        if (phone == null) {
            this.phone = null;
            return;
        }
        String num = phone.replaceAll("[^0-9]", "");
        if (num.length() == 10) {
            this.phone = "8" + num;
        } else {
            this.phone = null;
        }
    }

    public GenderDTO getSex() {
        return sex;
    }

    public void setSex(GenderDTO sex) {
        this.sex = sex;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayString() {
        if (birthday == null) {
            return null;
        }
        return getFormatter().format(birthday);
    }

    public void setBirthdayString(String birthdayString) {
        try {
            birthday = getFormatter().parse(birthdayString);
        } catch (ParseException e) {
            birthday = null;
        }
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getAddressVtb() {
        return addressVtb;
    }

    public void setAddressVtb(String addressVtb) {
        this.addressVtb = addressVtb;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public boolean isMailer() {
        return mailer;
    }

    public void setMailer(boolean mailer) {
        this.mailer = mailer;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getClientInfoSource() {
        return clientInfoSource;
    }

    public void setClientInfoSource(String clientInfoSource) {
        this.clientInfoSource = clientInfoSource;
    }

    public PartnerElement getRegistrationPartner() {
        return registrationPartner;
    }

    public void setRegistrationPartner(PartnerElement registrationPartner) {
        this.registrationPartner = registrationPartner;
    }

    public List<CobrandingElement> getCobradings() {
        return cobradings;
    }

    public void setCobradings(List<CobrandingElement> cobradings) {
        this.cobradings = cobradings;
    }

    public String getDocumentsJson() {
        return getGson().toJson(getDocuments());
    }

    public List<DocumentElementDTO> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<DocumentElementDTO>();
        }

        return documents;
    }

    public void setDocuments(List<DocumentElementDTO> documents) {
        this.documents = documents;
    }

    public List<ProgramElementDTO> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<ProgramElementDTO>();
        }
        return programs;
    }

    public void setPrograms(List<ProgramElementDTO> programs) {
        this.programs = programs;
    }


    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getAdditionStatus() {
        return additionStatus;
    }

    public void setAdditionStatus(String additionStatus) {
        this.additionStatus = additionStatus;
    }

    public Date getLastAccountOperationDate() {
        return lastAccountOperationDate;
    }

    public void setLastAccountOperationDate(Date lastAccountOperationDate) {
        this.lastAccountOperationDate = lastAccountOperationDate;
    }

    public String getLang() {
		return lang;
	}
    
    public void setLang(String lang) {
		this.lang = lang;
	}

    @Override
    public String toString() {
        return "ProfileElementDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                ", transliterationMismatch=" + transliterationMismatch +
                ", swindler=" + swindler +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", birthday=" + birthday +
                ", birthplace='" + birthplace + '\'' +
                ", codeWord='" + codeWord + '\'' +
                ", mailer=" + mailer +
                ", confirm=" + confirm +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", homePage='" + homePage + '\'' +
                ", job='" + job + '\'' +
                ", position='" + position + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", clientInfoSource='" + clientInfoSource + '\'' +
                ", registrationPartner=" + registrationPartner +
                ", cobradings=" + cobradings +
                ", cardInfo='" + cardInfo + '\'' +
                ", lastAccountOperationDate=" + lastAccountOperationDate +
                ", additionStatus='" + additionStatus + '\'' +
                ", language='" + lang + '\'' +
                ", sex=" + sex +
                ", address=" + address +
                ", documents=" + documents +
                ", programs=" + programs +
                ", secretBlock=" + secretBlock +
                '}';
    }

    private String printDocumentList(){
        String s = "\n\tDocuments : amount=" + documents.size();
        for(DocumentElementDTO doc: documents){
            s += "\n\t" + doc.toString();
        }
        return s + "\n";
    }

    private String printProgramList(){
        String s = "\n\tPrograms : amount=" + programs.size();
        for(ProgramElementDTO prog: programs){
            s += "\n\t" + prog.toString();
        }
        return s + "\n";
    }
}