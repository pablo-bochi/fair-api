package com.bochi.fairapi.domain

import com.bochi.fairapi.Fixture
import com.bochi.fairapi.core.exception.InvalidInputException
import com.bochi.fairapi.core.exception.ResourceAlreadyExistsException
import com.bochi.fairapi.core.exception.ResourceNotFoundException
import com.bochi.fairapi.presentation.dto.FairCreateDTO
import com.bochi.fairapi.presentation.dto.FairFilter
import com.bochi.fairapi.presentation.dto.FairPartialDTO
import com.bochi.fairapi.presentation.dto.FairUpdateDTO
import com.bochi.fairapi.presentation.FairService
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Shared
import spock.lang.Specification
import static java.util.Arrays.asList

class FairServiceTest extends Specification {

    @Shared FairRepository fairRepository

    @Shared FairCreateDTO createDTO = Fixture.createDTO()
    @Shared Fair fair = Fixture.fair()
    @Shared FairFilter fairFilter = Fixture.fairFilter()
    @Shared FairUpdateDTO updateDTO = Fixture.updateDTO()
    @Shared Fair fairUpdated = Fixture.fairUpdated()
    @Shared FairPartialDTO partialDTO = Fixture.partialDTO()
    @Shared Fair fairPartiallyUpdated = Fixture.fairPartiallyUpdated()

    void setup() {
        fairRepository = Mock(FairRepository)
    }

    def "should create fair with success" () {
        given:
        FairService service = getService()

        when:
        def result = service.create(createDTO)

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.empty()
        1 * fairRepository.save(fair) >> fair
        0 * _

        and:
        assert result == fair
    }

    def "should not create fair and throw ResourceAlreadyExistsException" () {
        given:
        FairService service = getService()

        when:
        def result = service.create(createDTO)

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.of(fair)
        ResourceAlreadyExistsException e = thrown(ResourceAlreadyExistsException)

        and:
        assert e.message == "Feira já existe para o registro: 4041-0"
    }

    def "should violate constraint when creating fair and throw InvalidInputException" () {
        given:
        FairService service = getService()

        when:
        def result = service.create(createDTO)

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.empty()
        1 * fairRepository.save(fair) >> { throw new InvalidInputException("Invalid fair!!") }
        0 * _

        and:
        InvalidInputException e = thrown(InvalidInputException)

        and:
        assert e.message == "Invalid fair!!"
    }

    def "should get fair by register code with success" () {
        given:
        FairService service = getService()

        when:
        def result = service.getByRegisterCode("4041-0")

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.of(fair)
        0 * _

        and:
        assert result == fair
    }

    def "should not find fair and throw ResourceNotFoundException" () {
        given:
        FairService service = getService()

        when:
        def result = service.getByRegisterCode("4041-0")

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.empty()
        ResourceNotFoundException e = thrown(ResourceNotFoundException)

        and:
        assert e.message == "Feira não encontrada para registro: 4041-0"
    }

    def "should find page of fairs by filter with success" () {
        given:
        FairService service = getService()
        def page = PageRequest.of(0, 1)

        when:
        def result = service.findAllByFilter(page, fairFilter)

        then:
        1 * fairRepository.findAll(_ as org.springframework.data.jpa.domain.Specification, page) >> new PageImpl<>(asList(fair))
        0 * _

        and:
        assert result.number == 0
        assert result.totalPages == 1
        assert result.totalElements == 1
        assert result.content.size() == 1
        assert result == new PageImpl<>(asList(fair))
    }

    def "should delete fair with success" () {
        given:
        FairService service = getService()

        when:
        service.delete("4041-0")

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.of(fair)
        1 * fairRepository.delete(fair)
        0 * _
    }

    def "should update whole fair object except register code with success" () {
        given:
        FairService service = getService()

        when:
        def result = service.update("4041-0", updateDTO)

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.of(fair)
        1 * fairRepository.save(fairUpdated) >> fairUpdated
        0 * _

        and:
        assert result == fairUpdated
    }

    def "should update only one field of fair with success" () {
        given:
        FairService service = getService()

        when:
        def result = service.updatePartial("4041-0", partialDTO)

        then:
        1 * fairRepository.findByRegisterCode("4041-0") >> Optional.of(fair)
        1 * fairRepository.save(fairPartiallyUpdated) >> fairPartiallyUpdated
        0 * _

        and:
        assert result == fairPartiallyUpdated
    }

    private FairServiceImpl getService() {
        return new FairServiceImpl(fairRepository)
    }

}
