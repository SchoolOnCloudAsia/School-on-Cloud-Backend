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

#pragma once

#include "mdc.h"
#include "grt.h"

#include <grtpp_undo_manager.h>

#include "grts/structs.model.h"

#include "wbpublic_public_interface.h"

#include "model_object_impl.h"

class WBPUBLICBACKEND_PUBLIC_FUNC model_Layer::ImplData : public model_Object::ImplData {
  typedef model_Object::ImplData super;

  friend class ViewBase;

protected:
  mdc::AreaGroup *_area_group;

  mdc::CanvasView *get_canvas_view() const;
  bool is_canvas_view_valid();

  void layer_bounds_changed(const base::Rect &rect);
  void interactive_layer_resized(const base::Rect &rect);

  virtual bool is_realizable();

  void member_changed(const std::string &name, const grt::ValueRef &ovalue);

public:
  ImplData(model_Layer *owner);

  virtual ~ImplData();

  void raise_figure(const model_FigureRef &figure);
  void lower_figure(const model_FigureRef &figure);

public:
  mdc::AreaGroup *get_area_group() const {
    return _area_group;
  }
  virtual mdc::CanvasItem *get_canvas_item() const {
    return _area_group;
  }

  virtual void render_mini(mdc::CairoCtx *cr);
  virtual bool realize();
  virtual void unrealize();

private:
  model_Layer *self() const {
    return (model_Layer *)_self;
  }
};
