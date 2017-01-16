package gov.hor.approp.controller;

import gov.hor.approp.model.Agency;
import gov.hor.approp.model.Award;
import gov.hor.approp.model.Bureau;
import gov.hor.approp.model.Congress;
import gov.hor.approp.model.Member;
import gov.hor.approp.model.Program;
import gov.hor.approp.model.State;
import gov.hor.approp.model.Subcommittee;
import gov.hor.approp.model.dao.FormFieldsDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@RestController
@RequestMapping("/form")
public class FormController {
    @Autowired
    private FormFieldsDAO repo;

    @RequestMapping(value = "/agencies/{subcommittee}", method = RequestMethod.GET)
    public @ResponseBody List<Agency> getAgencies(
            @PathVariable List<String> subcommittee) {
        List<Agency> agencies = repo.getAgencies(subcommittee);
        return agencies;
    }

    @RequestMapping(value = "/bureaus/{subcommittee}/{agency}", method = RequestMethod.GET)
    public @ResponseBody List<Bureau> getBureaus(@PathVariable List<String> agency, @PathVariable List<String> subcommittee) {
        List<Bureau> bureaus = repo.getBureaus(agency,subcommittee);
        return bureaus;
    }

    @RequestMapping(value = "/subcommittees", method = RequestMethod.GET)
    public @ResponseBody List<Subcommittee> getSubcommittees() {
        List<Subcommittee> subs = repo.getSubcommittees();
        return subs;
    }

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    public @ResponseBody List<State> getStates() {
        List<State> subs = repo.getStates();
        return subs;
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public @ResponseBody List<Member> getMembers(@RequestParam String partial,
            @RequestParam Integer congress, @RequestParam String state) {
        List<Member> subs = repo.getMemberAutoComplete(partial, congress, state);
        return subs;
    }

    /*
     * @RequestMapping(value = "/programs", method = RequestMethod.GET) public
     * 
     * @ResponseBody List<Program> getPrograms(@RequestParam String partial) {
     * List<Program> subs = repo.getCfdaAutoComplete(partial); return subs; }
     */

    @RequestMapping(value = "/programs/{subcommittee}/{agency}/{bureau}", method = RequestMethod.GET)
    public @ResponseBody List<Program> getPrograms(@PathVariable List<String> bureau,@PathVariable List<String> agency, @PathVariable List<String> subcommittee) {
        List<Program> subs = repo.getPrograms(bureau,subcommittee,agency);
        return subs;
    }
    
 /*   String csvFileName = "dvof.csv";
    response.setContentType("text/csv");
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"",
            csvFileName);
    response.setHeader(headerKey, headerValue);
    try {
		response.getWriter().print(csv);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/

    @RequestMapping(value = "/downloadCSV/{fy}")
    public void downloadCSV(@PathVariable List<String> fy, HttpServletResponse response) throws IOException{
    	String csvFileName = "programs.csv";
    	 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
 
 
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = { "gid", "award_date", "award_amount", "fiscal_year",
                "grant_or_subgrant", "assistance_type", "cfda","subcommittee", "program_title", "agency_name", "bureau_name",
                "recipient_name", "address", "city",
                "state", "zip", "zip4", "cd_at_award",
                "member_at_award", "party_at_award", "cd_current","member_currentid", "member_current", "party_current", "usa_spending_cd", "y","x"};
 
        csvWriter.writeHeader(header);
        List<Award>awards = repo.getAwards(fy);
 
        for (Award award : awards) {
            csvWriter.write(award, header);
        }
 
        csvWriter.close();
    }
    
    @RequestMapping(value = "/congresses", method = RequestMethod.GET)
    public @ResponseBody List<Congress> getCongresses() {
        List<Congress> subs = repo.getCongresses();
        return subs;
    }

    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public @ResponseBody List<String> getYears() {
        List<String> subs = repo.getYears();
        return subs;
    }

}
