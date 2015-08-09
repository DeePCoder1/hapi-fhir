package org.hl7.fhir.instance.validation;

/*
 Copyright (c) 2011+, HL7, Inc
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
 list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 this list of conditions and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
 endorse or promote products derived from this software without specific 
 prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.

 */

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.IssueType;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;

public class BaseValidator {

  protected Source source;

  /**
   * Test a rule and add a {@link IssueSeverity#FATAL} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean fail(List<ValidationMessage> errors, IssueType type, int line, int col, String path, boolean thePass, String msg) {
    return test(errors, type, line, col, path, thePass, msg, IssueSeverity.FATAL);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#FATAL} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean fail(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String msg) {
    return test(errors, type, pathParts, thePass, msg, IssueSeverity.FATAL);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#FATAL} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean fail(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String theMessage, Object... theMessageArguments) {
    return test(errors, type, pathParts, thePass, theMessage, IssueSeverity.FATAL, theMessageArguments);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#FATAL} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean fail(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg) {
    return test(errors, type, path, thePass, msg, IssueSeverity.FATAL);
  }
  
  private String formatMessage(String theMessage, Object... theMessageArguments) {
    String message;
    if (theMessageArguments != null && theMessageArguments.length > 0) {
      message = MessageFormat.format(theMessage, theMessageArguments);
    } else { 
      message = theMessage;
    }
    return message;
  }

  protected boolean grammarWord(String w) {
    return w.equals("and") || w.equals("or") || w.equals("a") || w.equals("the") || w.equals("for") || w.equals("this") || w.equals("that") || w.equals("of");
  }

  /**
   * Test a rule and add a {@link IssueSeverity#INFORMATION} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean hint(List<ValidationMessage> errors, IssueType type, int line, int col, String path, boolean thePass, String msg) {
    return test(errors, type, line, col, path, thePass, msg, IssueSeverity.INFORMATION);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#INFORMATION} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean hint(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String theMessage, Object... theMessageArguments) {
    return test(errors, type, pathParts, thePass, theMessage, IssueSeverity.INFORMATION, theMessageArguments);
  }


  /**
   * Test a rule and add a {@link IssueSeverity#INFORMATION} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean hint(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg) {
    return test(errors, type, path, thePass, msg, IssueSeverity.INFORMATION);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#ERROR} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean rule(List<ValidationMessage> errors, IssueType type, int line, int col, String path, boolean thePass, String msg) {
    return test(errors, type, line, col, path, thePass, msg, IssueSeverity.ERROR);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#ERROR} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean rule(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String msg) {
    return test(errors, type, pathParts, thePass, msg, IssueSeverity.ERROR);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#ERROR} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean rule(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String theMessage, Object... theMessageArguments) {
    return test(errors, type, pathParts, thePass, theMessage, IssueSeverity.ERROR, theMessageArguments);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#ERROR} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean rule(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String theMessage) {
    return test(errors, type, path, thePass, theMessage, IssueSeverity.ERROR);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#ERROR} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean rule(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg, String html) {
    return test(errors, type, path, thePass, msg, html, IssueSeverity.ERROR);
  }

  protected String splitByCamelCase(String s) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isUpperCase(c) && !(i == 0 || Character.isUpperCase(s.charAt(i - 1))))
        b.append(' ');
      b.append(c);
    }
    return b.toString();
  }

  protected String stripPunctuation(String s, boolean numbers) {
    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      int t = Character.getType(c);
      if (t == Character.UPPERCASE_LETTER || t == Character.LOWERCASE_LETTER || t == Character.TITLECASE_LETTER || t == Character.MODIFIER_LETTER || t == Character.OTHER_LETTER || (t == Character.DECIMAL_DIGIT_NUMBER && numbers) || (t == Character.LETTER_NUMBER && numbers) || c == ' ')
        b.append(c);
    }
    return b.toString();
  }

  protected boolean test(List<ValidationMessage> errors, IssueType type, int line, int col, String path,
      boolean thePass, String msg, IssueSeverity issueSeverity) {
    if (!thePass) {
      errors.add(new ValidationMessage(source, type, line, col, path, msg, issueSeverity));
    }
    return thePass;
  }

  protected boolean test(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass,
      String msg, IssueSeverity issueSeverity) {
    if (!thePass) {
      String path = toPath(pathParts);
      errors.add(new ValidationMessage(source, type, -1, -1, path, msg, issueSeverity));
    }
    return thePass;
  }

  protected boolean test(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass,
      String theMessage, IssueSeverity issueSeverity, Object... theMessageArguments) {
    if (!thePass) {
      String path = toPath(pathParts);
      errors.add(new ValidationMessage(source, type, -1, -1, path, formatMessage(theMessage, theMessageArguments), issueSeverity));
    }
    return thePass;
  }

  protected boolean test(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg,
      IssueSeverity issueSeverity) {
    if (!thePass) {
      errors.add(new ValidationMessage(source, type, -1, -1, path, msg, issueSeverity));
    }
    return thePass;
  }

  protected boolean test(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg,
      String html, IssueSeverity issueSeverity) {
    if (!thePass) {
      errors.add(new ValidationMessage(source, type, -1, -1, path, msg, html, issueSeverity));
    }
    return thePass;
  }

  private String toPath(List<String> pathParts) {
    if (pathParts == null || pathParts.isEmpty()) {
      return "";
    }
    return "//" + StringUtils.join(pathParts, '/');
  }

  /**
   * Test a rule and add a {@link IssueSeverity#WARNING} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean warning(List<ValidationMessage> errors, IssueType type, int line, int col, String path, boolean thePass, String msg) {
    return test(errors, type, line, col, path, thePass, msg, IssueSeverity.WARNING);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#WARNING} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean warning(List<ValidationMessage> errors, IssueType type, List<String> pathParts, boolean thePass, String theMessage, Object... theMessageArguments) {
    return test(errors, type, pathParts, thePass, theMessage, IssueSeverity.WARNING, theMessageArguments);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#WARNING} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean warning(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg) {
    return test(errors, type, path, thePass, msg, IssueSeverity.WARNING);
  }

  /**
   * Test a rule and add a {@link IssueSeverity#WARNING} validation message if the validation fails
   * 
   * @param thePass
   *          Set this parameter to <code>false</code> if the validation does not pass
   * @return Returns <code>thePass</code> (in other words, returns <code>true</code> if the rule did not fail validation)
   */
  protected boolean warning(List<ValidationMessage> errors, IssueType type, String path, boolean thePass, String msg, String html) {
    return test(errors, type, path, thePass, msg, html, IssueSeverity.WARNING);
  }

}