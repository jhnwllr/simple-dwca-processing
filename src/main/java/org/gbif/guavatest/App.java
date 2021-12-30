package org.gbif.dwcatest;

import com.google.common.base.Strings;
import com.google.common.base.CharMatcher;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.gbif.common.parsers.date.DateParsers;
import org.gbif.common.parsers.date.TemporalParser;
import org.gbif.common.parsers.core.ParseResult;

import org.gbif.dwc.Archive;
import org.gbif.dwc.DwcFiles;
import org.gbif.dwc.record.Record;
import org.gbif.dwc.terms.DwcTerm;


/**
 * Simple DWCA processing example using GBIF java packages. 
 *
 */
public class App 
{
		
    public static void main( String[] args ) throws IOException {	
	    Path myArchiveFile = Paths.get("dwca.zip");
		Path extractToFolder = Paths.get("/tmp/myarchive");
		Archive dwcArchive = DwcFiles.fromCompressed(myArchiveFile, extractToFolder);	
		
		TemporalParser dateParser = DateParsers.defaultTemporalParser();
		for (Record rec : dwcArchive.getCore()) {
			
		String id = rec.id();
		String year = rec.value(DwcTerm.year);
		String month = rec.value(DwcTerm.month);
		String day = rec.value(DwcTerm.day);
		
		ParseResult<TemporalAccessor> ta = dateParser.parse(year, month, day);
        LocalDate localDate = LocalDate.from(ta.getPayload());
		localDate = LocalDate.from(ta.getPayload());

		System.out.println(String.format("%s %s %s %s %s", id, year, month, day,localDate));
		}
		
	  }
}
