package fon.bg.ac.rs.fpis.trunks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleResponse {

    @JsonProperty("iss")
    private String iss;

    @JsonProperty("azp")
    private String azp;

    @JsonProperty("aud")
    private String aud;

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private String email_verified;

    @JsonProperty("at_hash")
    private String at_hash;

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("given_name")
    private String given_name;

    @JsonProperty("family_name")
    private String family_name;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("iat")
    private String iat;

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("jti")
    private String jti;

    @JsonProperty("alg")
    private String alg;

    @JsonProperty("kid")
    private String kid;

    @JsonProperty("typ")
    private String typ;

    public String getIss() {
        return iss;
    }

    public String getAzp() {
        return azp;
    }

    public String getAud() {
        return aud;
    }

    public String getSub() {
        return sub;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public String getAt_hash() {
        return at_hash;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getGiven_name() {
        return given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getLocale() {
        return locale;
    }

    public String getIat() {
        return iat;
    }

    public String getExp() {
        return exp;
    }

    public String getJti() {
        return jti;
    }

    public String getAlg() {
        return alg;
    }

    public String getKid() {
        return kid;
    }

    public String getTyp() {
        return typ;
    }

    @Override
    public String toString() {
        return "GoogleResponse{" +
                "iss='" + iss + '\'' +
                ", azp='" + azp + '\'' +
                ", aud='" + aud + '\'' +
                ", sub='" + sub + '\'' +
                ", email='" + email + '\'' +
                ", email_verified='" + email_verified + '\'' +
                ", at_hash='" + at_hash + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", given_name='" + given_name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", locale='" + locale + '\'' +
                ", iat='" + iat + '\'' +
                ", exp='" + exp + '\'' +
                ", jti='" + jti + '\'' +
                ", alg='" + alg + '\'' +
                ", kid='" + kid + '\'' +
                ", typ='" + typ + '\'' +
                '}';
    }
}
