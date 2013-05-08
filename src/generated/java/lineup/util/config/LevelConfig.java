//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.07 at 04:09:29 PM BST 
//


package lineup.util.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LevelConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LevelConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="base" type="{http://lineup/util/config}Waypoint"/>
 *         &lt;element name="route" type="{http://lineup/util/config}Route"/>
 *         &lt;element name="background" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="waves" type="{http://lineup/util/config}WaveList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LevelConfig", propOrder = {
    "base",
    "route",
    "background",
    "waves"
})
public class LevelConfig {

    @XmlElement(required = true)
    protected Waypoint base;
    @XmlElement(required = true)
    protected Route route;
    @XmlElement(required = true)
    protected String background;
    @XmlElement(required = true)
    protected WaveList waves;
    @XmlAttribute(name = "sequence")
    protected Integer sequence;

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link Waypoint }
     *     
     */
    public Waypoint getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link Waypoint }
     *     
     */
    public void setBase(Waypoint value) {
        this.base = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link Route }
     *     
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link Route }
     *     
     */
    public void setRoute(Route value) {
        this.route = value;
    }

    /**
     * Gets the value of the background property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackground(String value) {
        this.background = value;
    }

    /**
     * Gets the value of the waves property.
     * 
     * @return
     *     possible object is
     *     {@link WaveList }
     *     
     */
    public WaveList getWaves() {
        return waves;
    }

    /**
     * Sets the value of the waves property.
     * 
     * @param value
     *     allowed object is
     *     {@link WaveList }
     *     
     */
    public void setWaves(WaveList value) {
        this.waves = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSequence(Integer value) {
        this.sequence = value;
    }

}
