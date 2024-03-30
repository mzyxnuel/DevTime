//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//


package timecode.model.responses;

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
 *         <element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         <element name="incremental_percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="project_names_container">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="project_container" maxOccurs="unbounded">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         <element name="dates_container">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="date_container" maxOccurs="31">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="date" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             <sequence>
 *                               <element name="project_container" maxOccurs="unbounded">
 *                                 <complexType>
 *                                   <complexContent>
 *                                     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       <sequence>
 *                                         <element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                                         <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       </sequence>
 *                                     </restriction>
 *                                   </complexContent>
 *                                 </complexType>
 *                               </element>
 *                             </sequence>
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
 *         <element name="oss_container">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="os_container" maxOccurs="unbounded">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="os_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
 *         <element name="languages_container">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="language_container" maxOccurs="unbounded">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="language_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "state",
    "name",
    "time",
    "incrementalPercentage",
    "projectNamesContainer",
    "datesContainer",
    "ossContainer",
    "languagesContainer"
})
@XmlRootElement(name = "response")
public class ResponseDashboard {

    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    protected String name;
    protected long time;
    @XmlElement(name = "incremental_percentage")
    protected double incrementalPercentage;
    @XmlElement(name = "project_names_container", required = true)
    protected ResponseDashboard.ProjectNamesContainer projectNamesContainer;
    @XmlElement(name = "dates_container", required = true)
    protected ResponseDashboard.DatesContainer datesContainer;
    @XmlElement(name = "oss_container", required = true)
    protected ResponseDashboard.OssContainer ossContainer;
    @XmlElement(name = "languages_container", required = true)
    protected ResponseDashboard.LanguagesContainer languagesContainer;

    /**
     * Gets the value of the state property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the time property.
     *
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     */
    public void setTime(long value) {
        this.time = value;
    }

    /**
     * Gets the value of the incrementalPercentage property.
     *
     */
    public double getIncrementalPercentage() {
        return incrementalPercentage;
    }

    /**
     * Sets the value of the incrementalPercentage property.
     *
     */
    public void setIncrementalPercentage(double value) {
        this.incrementalPercentage = value;
    }

    /**
     * Gets the value of the projectNamesContainer property.
     *
     * @return
     *     possible object is
     *     {@link ResponseDashboard.ProjectNamesContainer }
     *
     */
    public ResponseDashboard.ProjectNamesContainer getProjectNamesContainer() {
        return projectNamesContainer;
    }

    /**
     * Sets the value of the projectNamesContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseDashboard.ProjectNamesContainer }
     *
     */
    public void setProjectNamesContainer(ResponseDashboard.ProjectNamesContainer value) {
        this.projectNamesContainer = value;
    }

    /**
     * Gets the value of the datesContainer property.
     *
     * @return
     *     possible object is
     *     {@link ResponseDashboard.DatesContainer }
     *
     */
    public ResponseDashboard.DatesContainer getDatesContainer() {
        return datesContainer;
    }

    /**
     * Sets the value of the datesContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseDashboard.DatesContainer }
     *
     */
    public void setDatesContainer(ResponseDashboard.DatesContainer value) {
        this.datesContainer = value;
    }

    /**
     * Gets the value of the ossContainer property.
     *
     * @return
     *     possible object is
     *     {@link ResponseDashboard.OssContainer }
     *
     */
    public ResponseDashboard.OssContainer getOssContainer() {
        return ossContainer;
    }

    /**
     * Sets the value of the ossContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseDashboard.OssContainer }
     *
     */
    public void setOssContainer(ResponseDashboard.OssContainer value) {
        this.ossContainer = value;
    }

    /**
     * Gets the value of the languagesContainer property.
     *
     * @return
     *     possible object is
     *     {@link ResponseDashboard.LanguagesContainer }
     *
     */
    public ResponseDashboard.LanguagesContainer getLanguagesContainer() {
        return languagesContainer;
    }

    /**
     * Sets the value of the languagesContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseDashboard.LanguagesContainer }
     *
     */
    public void setLanguagesContainer(ResponseDashboard.LanguagesContainer value) {
        this.languagesContainer = value;
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
     *         <element name="date_container" maxOccurs="31">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="date" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   <sequence>
     *                     <element name="project_container" maxOccurs="unbounded">
     *                       <complexType>
     *                         <complexContent>
     *                           <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                             <sequence>
     *                               <element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                               <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             </sequence>
     *                           </restriction>
     *                         </complexContent>
     *                       </complexType>
     *                     </element>
     *                   </sequence>
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
        "dateContainer"
    })
    public static class DatesContainer {

        @XmlElement(name = "date_container", required = true)
        protected List<ResponseDashboard.DatesContainer.DateContainer> dateContainer;

        /**
         * Gets the value of the dateContainer property.
         *
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dateContainer property.</p>
         *
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getDateContainer().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseDashboard.DatesContainer.DateContainer }
         * </p>
         *
         *
         * @return
         *     The value of the dateContainer property.
         */
        public List<ResponseDashboard.DatesContainer.DateContainer> getDateContainer() {
            if (dateContainer == null) {
                dateContainer = new ArrayList<>();
            }
            return this.dateContainer;
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
         *         <element name="date" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         <sequence>
         *           <element name="project_container" maxOccurs="unbounded">
         *             <complexType>
         *               <complexContent>
         *                 <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                   <sequence>
         *                     <element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *                     <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   </sequence>
         *                 </restriction>
         *               </complexContent>
         *             </complexType>
         *           </element>
         *         </sequence>
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
            "date",
            "projectContainer"
        })
        public static class DateContainer {

            protected String date;
            @XmlElement(name = "project_container", required = true)
            protected List<ResponseDashboard.DatesContainer.DateContainer.ProjectContainer> projectContainer;

            /**
             * Gets the value of the date property.
             *
             */
            public String getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             *
             */
            public void setDate(String value) {
                this.date = value;
            }

            /**
             * Gets the value of the projectContainer property.
             *
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the projectContainer property.</p>
             *
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getProjectContainer().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseDashboard.DatesContainer.DateContainer.ProjectContainer }
             * </p>
             *
             *
             * @return
             *     The value of the projectContainer property.
             */
            public List<ResponseDashboard.DatesContainer.DateContainer.ProjectContainer> getProjectContainer() {
                if (projectContainer == null) {
                    projectContainer = new ArrayList<>();
                }
                return this.projectContainer;
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
             *         <element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
             *         <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "time",
                "projectName"
            })
            public static class ProjectContainer {

                protected long time;
                @XmlElement(name = "project_name", required = true)
                protected String projectName;

                /**
                 * Gets the value of the time property.
                 *
                 */
                public long getTime() {
                    return time;
                }

                /**
                 * Sets the value of the time property.
                 *
                 */
                public void setTime(long value) {
                    this.time = value;
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

            }

        }

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
     *         <element name="language_container" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="language_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
        "languageContainer"
    })
    public static class LanguagesContainer {

        @XmlElement(name = "language_container", required = true)
        protected List<ResponseDashboard.LanguagesContainer.LanguageContainer> languageContainer;

        /**
         * Gets the value of the languageContainer property.
         *
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the languageContainer property.</p>
         *
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getLanguageContainer().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseDashboard.LanguagesContainer.LanguageContainer }
         * </p>
         *
         *
         * @return
         *     The value of the languageContainer property.
         */
        public List<ResponseDashboard.LanguagesContainer.LanguageContainer> getLanguageContainer() {
            if (languageContainer == null) {
                languageContainer = new ArrayList<>();
            }
            return this.languageContainer;
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
         *         <element name="language_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
            "languageName",
            "percentage"
        })
        public static class LanguageContainer {

            @XmlElement(name = "language_name", required = true)
            protected String languageName;
            protected double percentage;

            /**
             * Gets the value of the languageName property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLanguageName() {
                return languageName;
            }

            /**
             * Sets the value of the languageName property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLanguageName(String value) {
                this.languageName = value;
            }

            /**
             * Gets the value of the percentage property.
             *
             */
            public double getPercentage() {
                return percentage;
            }

            /**
             * Sets the value of the percentage property.
             *
             */
            public void setPercentage(double value) {
                this.percentage = value;
            }

        }

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
     *         <element name="os_container" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="os_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
        "osContainer"
    })
    public static class OssContainer {

        @XmlElement(name = "os_container", required = true)
        protected List<ResponseDashboard.OssContainer.OsContainer> osContainer;

        /**
         * Gets the value of the osContainer property.
         *
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the osContainer property.</p>
         *
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getOsContainer().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseDashboard.OssContainer.OsContainer }
         * </p>
         *
         *
         * @return
         *     The value of the osContainer property.
         */
        public List<ResponseDashboard.OssContainer.OsContainer> getOsContainer() {
            if (osContainer == null) {
                osContainer = new ArrayList<>();
            }
            return this.osContainer;
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
         *         <element name="os_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         <element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
            "osName",
            "percentage"
        })
        public static class OsContainer {

            @XmlElement(name = "os_name", required = true)
            protected String osName;
            protected double percentage;

            /**
             * Gets the value of the osName property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getOsName() {
                return osName;
            }

            /**
             * Sets the value of the osName property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setOsName(String value) {
                this.osName = value;
            }

            /**
             * Gets the value of the percentage property.
             *
             */
            public double getPercentage() {
                return percentage;
            }

            /**
             * Sets the value of the percentage property.
             *
             */
            public void setPercentage(double value) {
                this.percentage = value;
            }

        }

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
     *         <element name="project_container" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "projectContainer"
    })
    public static class ProjectNamesContainer {

        @XmlElement(name = "project_container", required = true)
        protected List<ResponseDashboard.ProjectNamesContainer.ProjectContainer> projectContainer;

        /**
         * Gets the value of the projectContainer property.
         *
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the projectContainer property.</p>
         *
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getProjectContainer().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseDashboard.ProjectNamesContainer.ProjectContainer }
         * </p>
         *
         *
         * @return
         *     The value of the projectContainer property.
         */
        public List<ResponseDashboard.ProjectNamesContainer.ProjectContainer> getProjectContainer() {
            if (projectContainer == null) {
                projectContainer = new ArrayList<>();
            }
            return this.projectContainer;
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
         *         <element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "projectName"
        })
        public static class ProjectContainer {

            @XmlElement(name = "project_name", required = true)
            protected String projectName;

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

        }

    }

}
