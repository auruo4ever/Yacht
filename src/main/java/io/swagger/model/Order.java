package io.swagger.model;



import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-23T09:25:02.102Z[GMT]")

@Entity
@Table(name="orders")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Order   {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @JsonIgnore
  private Long id = null;

  @JsonProperty("bookingDateStart")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date bookingDateStart = null;

  @JsonProperty("bookingDateEnd")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date bookingDateEnd = null;

  @JsonProperty("complete")
  private Boolean complete = false;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("mail")
  private String mail = null;

  @JsonProperty("place")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "place_id")
  private Place place = new Place();
  
  public Order id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Order bookingDateStart(Date bookingDateStart) {
    this.bookingDateStart = bookingDateStart;
    return this;
  }


    public Date getBookingDateStart() {
    return bookingDateStart;
  }

  public void setBookingDateStart(Date bookingDateStart) {
    this.bookingDateStart = bookingDateStart;
  }

  public Order bookingDateEnd(Date bookingDateEnd) {
    this.bookingDateEnd = bookingDateEnd;
    return this;
  }

  /**
   * Get bookingDateEnd
   * @return bookingDateEnd
   **/
  @Schema(description = "")

  public Date getBookingDateEnd() {
    return bookingDateEnd;
  }

  public void setBookingDateEnd(Date bookingDateEnd) {
    this.bookingDateEnd = bookingDateEnd;
  }

  public Order complete(Boolean complete) {
    this.complete = complete;
    return this;
  }

  /**
   * Get complete
   * @return complete
   **/
  @Schema(description = "")
  
    public Boolean isComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.bookingDateStart, order.bookingDateStart) &&
        Objects.equals(this.bookingDateEnd, order.bookingDateEnd) &&
        Objects.equals(this.name, order.name) &&
        Objects.equals(this.complete, order.complete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bookingDateStart, bookingDateEnd, complete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bookingDateStart: ").append(toIndentedString(bookingDateStart)).append("\n");
    sb.append("    bookingDateEnd: ").append(toIndentedString(bookingDateEnd)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
