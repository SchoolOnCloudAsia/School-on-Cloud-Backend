/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License, version 2.0,
 * as published by the Free Software Foundation.
 *
 * This program is also distributed with certain software (including
 * but not limited to OpenSSL) that is licensed under separate terms, as
 * designated in a particular file or component or in included license
 * documentation.  The authors of MySQL hereby grant you an additional
 * permission to link the program and your derivative works with the
 * separately licensed software that they have included with MySQL.
 * This program is distributed in the hope that it will be useful,  but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See
 * the GNU General Public License, version 2.0, for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA 
 */

#ifndef __USER_EDITOR_H__
#define __USER_EDITOR_H__

#include "DBObjectEditorWrapper.h"
#include "RoleTreeBE.h"
#include "GrtTemplates.h"
#include "grtdb/editor_user.h"

namespace MySQL {
  namespace Grt {
    namespace Db {

    public
      ref class UserEditorBE : public BaseEditorWrapper {
      protected:
        UserEditorBE(::bec::UserEditorBE *inn);
        ~UserEditorBE();

      public:
        UserEditorBE::UserEditorBE(MySQL::Grt::GrtValue ^ arglist);
        ::bec::UserEditorBE *get_unmanaged_object();
        void set_name(String ^ name);
        String ^ get_name();
        void set_password(String ^ pass);
        String ^ get_password();
        void set_comment(String ^ comment);
        String ^ get_comment();
        RoleTreeBE ^ get_role_tree();
        void add_role(String ^ pass);
        void remove_role(String ^ pass);
        ;
        List<String ^> ^ get_roles();
      };

    } // namespace Db
  }   // namespace Grt
} // namespace MySQL

#endif // __USER_EDITOR_H__