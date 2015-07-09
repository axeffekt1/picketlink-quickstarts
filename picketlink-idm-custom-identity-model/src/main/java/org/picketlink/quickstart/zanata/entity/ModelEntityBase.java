/*
 * Copyright 2010, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.picketlink.quickstart.zanata.entity;

import org.picketlink.idm.jpa.annotations.AttributeValue;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

//import org.hibernate.search.annotations.Analyze;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.FieldBridge;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.zanata.hibernate.search.DateBridge;

//@EntityListeners({ModelEntityBase.EntityListener.class})
@MappedSuperclass
public class ModelEntityBase implements Serializable/*, HashableState*/ {

    private static final long serialVersionUID = -6139220551322868743L;
    protected Long id;

    protected Date creationDate;

    protected Date lastChanged;

    protected Integer versionNum;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(nullable = true)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result =
                prime
                        * result
                        + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result =
                prime * result
                        + ((lastChanged == null) ? 0 : lastChanged.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelEntityBase other = (ModelEntityBase) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastChanged == null) {
            if (other.lastChanged != null)
                return false;
        } else if (!lastChanged.equals(other.lastChanged))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@"
                + Integer.toHexString(hashCode()) + "[id=" + id
                + ",versionNum=" + versionNum + "]";
    }

    protected boolean logPersistence() {
        return true;
    }

//    @Override
    public void writeHashState(ByteArrayOutputStream buff) throws IOException {
        buff.write(versionNum.byteValue());
    }

//    public static class EntityListener {
//        @SuppressWarnings("unused")
//        @PrePersist
//        private void onPersist(ModelEntityBase meb) {
//            Date now = new Date();
//            if (meb.creationDate == null) {
//                meb.creationDate = now;
//            }
//            if (meb.lastChanged == null) {
//                meb.lastChanged = now;
//            }
//        }
//
//        @SuppressWarnings("unused")
//        @PostPersist
//        private void postPersist(ModelEntityBase meb) {
//            if (meb.logPersistence()) {
//                Logger log = LoggerFactory.getLogger(meb.getClass());
//                log.info("persist entity: {}", meb);
//            }
//        }
//
//        @SuppressWarnings("unused")
//        @PreUpdate
//        private void onUpdate(ModelEntityBase meb) {
//            meb.lastChanged = new Date();
//        }
//
//        @SuppressWarnings("unused")
//        @PreRemove
//        private void onRemove(ModelEntityBase meb) {
//            if (meb.logPersistence()) {
//                Logger log = LoggerFactory.getLogger(meb.getClass());
//                log.info("remove entity: {}", meb);
//            }
//        }
//
//    }
}
