//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//


package timecode.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type</p>.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 *
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="id_user" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="start_time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         <element name="end_time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="os" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="files">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="file" maxOccurs="unbounded">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             <element name="rows_count" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idUser",
    "startTime",
    "endTime",
    "projectName",
    "os",
    "files"
})
@XmlRootElement(name = "Activity")
public class Activity {

    @XmlElement(name = "id_user")
    protected int idUser;
    @XmlElement(name = "start_time")
    protected long startTime;
    @XmlElement(name = "end_time")
    protected long endTime;
    @XmlElement(name = "project_name", required = true)
    protected String projectName;
    @XmlElement(required = true)
    protected String os;
    @XmlElement(required = true)
    protected Activity.Files files;

    public Activity(int idUser, long startTime, long endTime, String projectName, String os, Files files) {
        this.idUser = idUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
        this.os = os;
        this.files = files;
    }

    /**
     * Gets the value of the idUser property.
     *
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     *
     */
    public void setIdUser(int value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the startTime property.
     *
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     *
     */
    public void setStartTime(long value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     *
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     *
     */
    public void setEndTime(long value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the projectName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the value of the projectName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * Gets the value of the os property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOs() {
        return os;
    }

    /**
     * Sets the value of the os property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOs(String value) {
        this.os = value;
    }

    /**
     * Gets the value of the files property.
     *
     * @return
     *     possible object is
     *     {@link Activity.Files }
     *
     */
    public Activity.Files getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     *
     * @param value
     *     allowed object is
     *     {@link Activity.Files }
     *
     */
    public void setFiles(Activity.Files value) {
        this.files = value;
    }


    /**
     * <p>Java class for anonymous complex type</p>.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     *
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="file" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   <element name="rows_count" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "file"
    })
    public static class Files {

        @XmlElement(required = true)
        protected List<Activity.Files.File> file;

        /**
         * Gets the value of the file property.
         *
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the file property.</p>
         *
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getFile().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Activity.Files.File }
         * </p>
         *
         *
         * @return
         *     The value of the file property.
         */
        public List<Activity.Files.File> getFile() {
            if (file == null) {
                file = new ArrayList<>();
            }
            return this.file;
        }


        /**
         * <p>Java class for anonymous complex type</p>.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.</p>
         *
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         <element name="rows_count" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fileName",
            "rowsCount"
        })
        public static class File {

            @XmlElement(name = "file_name", required = true)
            protected String fileName;
            @XmlElement(name = "rows_count", required = true)
            protected BigInteger rowsCount;

            /**
             * Gets the value of the fileName property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFileName() {
                return fileName;
            }

            /**
             * Sets the value of the fileName property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFileName(String value) {
                this.fileName = value;
            }

            /**
             * Gets the value of the rowsCount property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getRowsCount() {
                return rowsCount;
            }

            /**
             * Sets the value of the rowsCount property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setRowsCount(BigInteger value) {
                this.rowsCount = value;
            }

        }

    }

}
