package com.aluguelsala.model

import com.aluguelsala.enums.CustomerStatus
import com.aluguelsala.enums.Profile
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity(name="customer")
data class CustomerModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null,

  @Column
  var name: String,

  @Column
  var email: String,

  @Column
  @Enumerated(EnumType.STRING)
  var status: CustomerStatus,

  @Column
  var password: String,

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
  @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
  var roles: Set<Profile> = setOf()


){
  fun comparePassword(password: String): Boolean {
    return BCryptPasswordEncoder().matches(password, this.password)

  }
}