package com.bochi.fairapi.fair.web

import com.bochi.fairapi.Fixture
import com.bochi.fairapi.fair.Fair
import com.bochi.fairapi.fair.FairService
import com.bochi.fairapi.fair.dto.FairCreateDTO
import com.bochi.fairapi.fair.dto.FairFilter
import com.bochi.fairapi.fair.dto.FairPartialDTO
import com.bochi.fairapi.fair.dto.FairResponseDTO
import com.bochi.fairapi.fair.dto.FairUpdateDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static java.util.Arrays.asList

class FairRestServiceTest extends Specification {

    @Shared MockMvc mvc
    @Shared FairService fairService
    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared String baseUrl = "/fair/api"

    @Shared FairCreateDTO createDTO = Fixture.createDTO()
    @Shared Fair fair = Fixture.fair()
    @Shared FairResponseDTO responseDTO = Fixture.responseDTO()
    @Shared FairFilter filter = Fixture.fairFilter()
    @Shared FairUpdateDTO updateDTO = Fixture.updateDTO()
    @Shared Fair fairUpdated = Fixture.fairUpdated()
    @Shared FairPartialDTO partialDTO = Fixture.partialDTO()
    @Shared Fair fairPartiallyUpdated = Fixture.fairPartiallyUpdated()

    void setup() {
        fairService = Mock(FairService)
        mvc = MockMvcBuilders.standaloneSetup(new FairRestService(fairService)).build()
    }

    def "should create fair with success" () {
        when:
        def response = mvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createDTO)))

        then:
        1 * fairService.create(createDTO) >> fair
        0 * _

        and:
        response.andExpect(status().isCreated())
                .andReturn().response.contentAsString.contains(mapper.writeValueAsString(responseDTO))
    }

    def "should find fair by name with success" () {
        when:
        def response = mvc.perform(get(baseUrl + "/{fairName}", "VILA FORMOSA")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.getByName("VILA FORMOSA") >> fair
        0 * _

        and:
        response.andExpect(status().isOk())
                .andReturn().response.contentAsString.contains(mapper.writeValueAsString(responseDTO))
    }

    def "should find fair page by filter with success" () {
        when:
        def response = mvc.perform(get(baseUrl, filter.district, filter.fairName, filter.neighbourhood, filter.region5)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.findAllByFilter(_ as Pageable, filter) >> new PageImpl<>(asList(fair))
        0 * _

        and:
        response.andExpect(status().isOk())
                .andReturn().response.contentAsString.contains(mapper.writeValueAsString(new PageImpl<>(asList(fair))))
    }

    def "should update fair with success" () {
        when:
        def respose = mvc.perform(put(baseUrl + "/{registerCode}", "4041-0")
                .content(mapper.writeValueAsString(updateDTO))
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.update("4041-0", updateDTO) >> fairUpdated
        0 * _

        and:
        respose.andExpect(status().isOk())
                .andReturn().response.contentAsString.contains(mapper.writeValueAsString(FairResponseDTO.of(fairUpdated)))
    }

    def "should partially update fair with success" () {
        when:
        def respose = mvc.perform(patch(baseUrl + "/{registerCode}", "4041-0")
                .content(mapper.writeValueAsString(partialDTO))
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.updatePartial("4041-0", partialDTO) >> fairPartiallyUpdated
        0 * _

        and:
        respose.andExpect(status().isOk())
                .andReturn().response.contentAsString.contains(mapper.writeValueAsString(FairResponseDTO.of(fairPartiallyUpdated)))
    }

    def "should delete fair with success" () {
        when:
        def response = mvc.perform(delete(baseUrl + "/{registerCode}", "4041-0")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.delete("4041-0")

        and:
        response.andExpect(status().isOk())
    }

    def "should inactivate fair with success" () {
        when:
        def response = mvc.perform(delete(baseUrl + "/{registerCode}/inactivate", "4041-0")
                .contentType(MediaType.APPLICATION_JSON))

        then:
        1 * fairService.softDelete("4041-0")

        and:
        response.andExpect(status().isOk())
    }

}
