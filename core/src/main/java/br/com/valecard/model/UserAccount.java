/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;
    
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    
    @Column(name = "MOBILE", nullable = false, unique = true)
    private String mobile;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @CreatedDate
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
    
    @LastModifiedDate
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    private Date lastModifiedDate;

    //spring security
    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired;
    
    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    private boolean credentialsNonExpired;
    
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    
    @ManyToMany
    @JoinTable(name = "USER_ACCOUNT_ROLES")
    private Collection<Role> authorities;

    //ativacao e recuperacao de senha
    @Column(name = "CODE")
    private String code;
    
    @Column(name = "ACTIVATION_CODE")
    private String activationCode;
    
    @Column(name = "RECOVER_CODE")
    private String recoverCode;

    public UserAccount() {
    }

    public UserAccount(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * @param accountNonExpired the accountNonExpired to set
     */
    @JsonProperty(value = "accountNonExpired")
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        if (getCode() != null && getActivationCode() != null) {
            return getCode().equals(getActivationCode());
        } else {
            return false;
        }
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * @param credentialsNonExpired the credentialsNonExpired to set
     */
    @JsonProperty(value = "credentialsNonExpired")
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    @JsonProperty(value = "enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @JsonIgnore
    public Collection<Role> getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities the authorities to set
     */
    @JsonProperty(value = "authorities")
    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("fullName")
    public String getFullName() {
        return String.format("%1$s %2$s", firstName, lastName);
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the createDate
     */
    @JsonIgnore
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    @JsonProperty("createDate")
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the lastModifiedDate
     */
    @JsonIgnore
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate the lastModifiedDate to set
     */
    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @return the code
     */
    @JsonIgnore
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the activationCode
     */
    @JsonIgnore
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * @param activationCode the activationCode to set
     */
    @JsonProperty("activationCode")
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * @return the recoverCode
     */
    @JsonIgnore
    public String getRecoverCode() {
        return recoverCode;
    }

    /**
     * @param recoverCode the recoverCode to set
     */
    @JsonProperty("recoverCode")
    public void setRecoverCode(String recoverCode) {
        this.recoverCode = recoverCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAccount other = (UserAccount) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
